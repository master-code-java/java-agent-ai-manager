package com.oiseau.ai_agent_tester.service.impl;

import com.oiseau.ai_agent_tester.model.Agent;
import com.oiseau.ai_agent_tester.model.Project;
import com.oiseau.ai_agent_tester.repository.AgentRepository;
import com.oiseau.ai_agent_tester.repository.ProjectRepository;
import com.oiseau.ai_agent_tester.request.ProjectRequest;
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

    @Autowired
    private AgentRepository agentRepository;

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
        project.setUuid(UUID.randomUUID().toString());
        return projectRepository.save(project);
    }

    @Override
    public Optional<Project> updateProject(int id, Project projectDetails) {
        return projectRepository.findById(id).map(existingProject -> {
            if (projectDetails.getUuid() != null) {
                existingProject.setUuid(projectDetails.getUuid());

            }
            if (projectDetails.getAgents() != null && !projectDetails.getAgents().isEmpty()) {
                existingProject.setAgents(projectDetails.getAgents());

            }
            return projectRepository.save(existingProject);

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

    @Override
    public Project createProject(ProjectRequest projectRequest) {
        Project newProject = new Project();
        newProject.setUuid(UUID.randomUUID().toString());
        List<Agent> agents = agentRepository.findAllByUuidIn(projectRequest.getProjectsUUID());
        newProject.setAgents(agents);

        return projectRepository.save(newProject);
    }
}