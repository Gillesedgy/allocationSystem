package com.cas.clientAllocationSystem.controller;

import com.cas.clientAllocationSystem.dto.ProjectDto;
import com.cas.clientAllocationSystem.entity.ProjectAssignment;
import com.cas.clientAllocationSystem.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * Creates a new project under a specific contract.
     *
     * @param dto The project data.
     * @return The created ProjectDto.
     */
    @PostMapping
    public ProjectDto createProject(@RequestBody ProjectDto dto) {
        return projectService.createProject(dto);
    }

    /**
     * Fetches all projects.
     *
     * @return A list of ProjectDto objects.
     */
    @GetMapping
    public List<ProjectDto> getAllProjects() {
        return projectService.getAllProjects();
    }

    /**
     * Fetches projects associated with a specific contract.
     *
     * @param contractId The ID of the contract.
     * @return A list of ProjectDto objects for the given contract.
     */
    @GetMapping("/contract/{contractId}")
    public List<ProjectDto> getProjectsByContractId(@PathVariable Long contractId) {
        return projectService.getProjectsByContractId(contractId);
    }

    /**
     * Fetches all assignments for a specific project.
     *
     * @param projectId The ID of the project.
     * @return A list of assignments for the given project.
     */
    @GetMapping("/{projectId}/assignments")
    public List<ProjectAssignment> getAssignmentsForProject(@PathVariable Long projectId) {
        return projectService.getAssignmentsForProject(projectId);
    }
}


