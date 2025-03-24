package com.oiseau.ai_agent_tester.service.impl;

import com.oiseau.ai_agent_tester.model.Agent;
import com.oiseau.ai_agent_tester.model.Project;
import com.oiseau.ai_agent_tester.repository.AgentRepository;
import com.oiseau.ai_agent_tester.repository.ProjectRepository;
import com.oiseau.ai_agent_tester.request.ProjectRequest;
import com.oiseau.ai_agent_tester.response.OpenAiResponse;
import com.oiseau.ai_agent_tester.response.ProjectExecutionResponse;
import com.oiseau.ai_agent_tester.service.OpenAiService;
import com.oiseau.ai_agent_tester.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private OpenAiService openAiService;

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

    @Override
    public ProjectExecutionResponse runProject(String uuid, String input) {

        List<OpenAiResponse> openAIResponses = new ArrayList<>();

        projectRepository.findByUuid(uuid).ifPresent(project -> {
            String currentInput = input;
            for (Agent agent : project.getAgents()) {
                String response = openAiService.getResponse(agent.getContext(), currentInput);
                currentInput = extractContentFromResponse(response);

                openAIResponses.add(new OpenAiResponse(uuid, agent.getContext(), currentInput));
            
            }

        });
        

        return new ProjectExecutionResponse(UUID.randomUUID().toString(), input, openAIResponses);
    }

    private String extractContentFromResponse(String response) {
        try {
            com.fasterxml.jackson.databind.JsonNode jsonNode = 
                new com.fasterxml.jackson.databind.ObjectMapper().readTree(response);
            return jsonNode.path("choices").get(0).path("message").path("content").asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse OpenAI response", e);
        }
    }
}