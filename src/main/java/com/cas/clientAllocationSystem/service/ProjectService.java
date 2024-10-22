package com.cas.clientAllocationSystem.service;

import com.cas.clientAllocationSystem.dto.ProjectDto;
import com.cas.clientAllocationSystem.entity.Contract;
import com.cas.clientAllocationSystem.entity.Project;
import com.cas.clientAllocationSystem.entity.ProjectAssignment;
import com.cas.clientAllocationSystem.entity.Staff;
import com.cas.clientAllocationSystem.mapper.ProjectMapper;
import com.cas.clientAllocationSystem.repository.ContractRepository;
import com.cas.clientAllocationSystem.repository.ProjectAssignmentRepository;
import com.cas.clientAllocationSystem.repository.ProjectRepository;
import com.cas.clientAllocationSystem.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectAssignmentRepository assignmentRepository;
    private final StaffRepository staffRepository;
    private final ContractRepository contractRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository,
                          ProjectAssignmentRepository assignmentRepository,
                          StaffRepository staffRepository,
                          ContractRepository contractRepository) {
        this.projectRepository = projectRepository;
        this.assignmentRepository = assignmentRepository;
        this.staffRepository = staffRepository;
        this.contractRepository = contractRepository;
    }

    /**
     * Creates a new project under a specific contract.
     *
     * @param dto The ProjectDto containing project details.
     * @return The saved ProjectDto.
     */
    public ProjectDto createProject(ProjectDto dto) {
        Contract contract = contractRepository.findById(dto.getContractId())
                .orElseThrow(() -> new IllegalArgumentException("Contract not found"));

        Project project = ProjectMapper.INSTANCE.toProjectEntity(dto);
        project.setContract(contract);

        Project savedProject = projectRepository.save(project);
        return ProjectMapper.INSTANCE.toProjectDto(savedProject);
    }

    /**
     * Assigns available staff to a project, ensuring they are not already assigned.
     *
     * @param projectId The ID of the project.
     * @param staffId   The ID of the staff member.
     * @param startDate The assignment start date.
     * @param endDate   The assignment end date.
     */
    public void assignStaffToProject(Long projectId, Long staffId, LocalDate startDate, LocalDate endDate) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new IllegalArgumentException("Staff not found"));

        // Check if staff is already assigned to another active project
        List<ProjectAssignment> currentAssignments = assignmentRepository.findCurrentAssignments(staffId);
        if (!currentAssignments.isEmpty()) {
            throw new IllegalArgumentException("Staff is already assigned to another active project.");
        }

        // Create and save the new assignment
        ProjectAssignment assignment = new ProjectAssignment(
                null, project, staff, staff.getRole(), startDate, endDate, null);
        assignmentRepository.save(assignment);

        // Update staff's availability after the assignment ends
        staff.setAvailableFrom(endDate.plusDays(1));
        staffRepository.save(staff);
    }

    /**
     * Retrieves all projects.
     *
     * @return A list of all ProjectDto objects.
     */
    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(ProjectMapper.INSTANCE::toProjectDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves projects by contract ID.
     *
     * @param contractId The ID of the contract.
     * @return A list of ProjectDto objects under the specified contract.
     */
    public List<ProjectDto> getProjectsByContractId(Long contractId) {
        return projectRepository.findByContract_Id(contractId).stream()
                .map(ProjectMapper.INSTANCE::toProjectDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all assignments for a specific project.
     *
     * @param projectId The ID of the project.
     * @return A list of ProjectAssignment objects for the project.
     */
    public List<ProjectAssignment> getAssignmentsForProject(Long projectId) {
        return assignmentRepository.findById(projectId).stream().collect(Collectors.toList());
    }

    /**
     * Reassigns staff to another project or updates the assignment end date.
     *
     * @param assignmentId The ID of the assignment to update.
     * @param newEndDate   The new end date for the assignment.
     */
    public void reassignStaff(Long assignmentId, LocalDate newEndDate) {
        ProjectAssignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new IllegalArgumentException("Assignment not found"));
        assignment.setEndDate(newEndDate);
        assignmentRepository.save(assignment);
    }
}
