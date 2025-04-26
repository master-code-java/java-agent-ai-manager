package com.oiseau.ai_agent_tester.service;

import com.oiseau.ai_agent_tester.model.Project;
import com.oiseau.ai_agent_tester.response.ProjectExecutionResponse;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    List<Project> getAllProjects();

    Optional<Project> getProjectById(int id);

    Project createProject(Project project);

    ProjectExecutionResponse runProject(int project, String input);
}