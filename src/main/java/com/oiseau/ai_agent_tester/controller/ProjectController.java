package com.oiseau.ai_agent_tester.controller;

import com.oiseau.ai_agent_tester.model.Project;
import com.oiseau.ai_agent_tester.response.ProjectExecutionResponse;
import com.oiseau.ai_agent_tester.service.ProjectService;
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
    

    @PostMapping("/{id}/run")
    public ResponseEntity<ProjectExecutionResponse> runProject(
            @PathVariable int id,
            @RequestParam("input") String input) {
        ProjectExecutionResponse response = projectService.runProject(id, input);
        
        return ResponseEntity.ok().body(response);
    }
}