package com.cas.clientAllocationSystem.service;

import com.cas.clientAllocationSystem.entity.Project;
import com.cas.clientAllocationSystem.entity.ProjectAssignment;
import com.cas.clientAllocationSystem.entity.Staff;
import com.cas.clientAllocationSystem.repository.ProjectAssignmentRepository;
import com.cas.clientAllocationSystem.repository.ProjectRepository;
import com.cas.clientAllocationSystem.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectAssignmentRepository assignmentRepository;
    @Autowired
    private StaffRepository staffRepository;


    // 1. Assign available staff to a project based on role and skills
    public void assignStaffToProject(Long projectId, Long staffId, LocalDate startDate, LocalDate endDate) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new IllegalArgumentException("Staff not found"));
        List<ProjectAssignment> currentAssignments =
                assignmentRepository.findCurrentAssignments(staffId);
        if (!currentAssignments.isEmpty()) {
            throw new IllegalArgumentException("Staff is already assigned to another active project.");
        }

        // Create new project assignment
        ProjectAssignment assignment = new ProjectAssignment(null, project, staff, staff.getRole(), startDate, endDate, null);
        assignmentRepository.save(assignment);

        // Update staff availability
        staff.setAvailableFrom(endDate.plusDays(1)); // Available after assignment ends
        staffRepository.save(staff);
    }

    // 2. Get all assignments for a project
    public List<ProjectAssignment> getAssignmentsForProject(Long projectId) {
        return assignmentRepository.findByProjectId(projectId);
    }

    // 3. Reassign staff to another project (update dates)
    public void reassignStaff(Long assignmentId, LocalDate newEndDate) {
        ProjectAssignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new IllegalArgumentException("Assignment not found"));
        assignment.setEndDate(newEndDate);
        assignmentRepository.save(assignment);
    }
}