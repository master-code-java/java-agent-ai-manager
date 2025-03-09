package com.oiseau.ai_agent_tester.service.impl;

import com.oiseau.ai_agent_tester.model.Project;
import com.oiseau.ai_agent_tester.repository.ProjectRepository;
import com.oiseau.ai_agent_tester.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> getProjectById(int id) {
        return projectRepository.findById(id);
    }

    @Override
    public Project createProject(Project project) {
        project.setUuid(UUID.randomUUID());
        return projectRepository.save(project);
    }

    @Override
    public Optional<Project> updateProject(int id, Project projectDetails) {
        return projectRepository.findById(id).map(project -> {
            project.setAgents(projectDetails.getAgents());
            return projectRepository.save(project);
        });
    }

    @Override
    public boolean deleteProject(int id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}