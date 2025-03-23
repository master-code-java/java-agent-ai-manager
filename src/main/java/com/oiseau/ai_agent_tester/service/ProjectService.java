package com.oiseau.ai_agent_tester.service;

import com.oiseau.ai_agent_tester.model.Project;
import com.oiseau.ai_agent_tester.request.ProjectRequest;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    List<Project> getAllProjects();

    Optional<Project> getProjectById(int id);

    Project createProject(Project project);

    Project createProject(ProjectRequest project);

    Optional<Project> updateProject(int id, Project projectDetails);

    boolean deleteProject(int id);
}