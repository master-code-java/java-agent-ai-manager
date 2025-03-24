package com.oiseau.ai_agent_tester.controller;

import com.oiseau.ai_agent_tester.model.Project;
import com.oiseau.ai_agent_tester.request.ProjectRequest;
import com.oiseau.ai_agent_tester.response.ProjectExecutionResponse;
import com.oiseau.ai_agent_tester.service.ProjectService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable int id) {
        Optional<Project> project = projectService.getProjectById(id);
        return project.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Project createProjectRequest(@RequestBody ProjectRequest request) {
        return projectService.createProject(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable int id, @RequestBody Project projectDetails) {
        Optional<Project> updatedProject = projectService.updateProject(id, projectDetails);
        return updatedProject.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable int id) {
        if (projectService.deleteProject(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{uuid}/run")
    public ResponseEntity<ProjectExecutionResponse> runProject(
            @Parameter(description = "The UUID of the project to run") @PathVariable String uuid,
            @RequestParam("input") String input) {
        ProjectExecutionResponse response = projectService.runProject(uuid, input);
        
        return ResponseEntity.ok().body(response);
    }
}