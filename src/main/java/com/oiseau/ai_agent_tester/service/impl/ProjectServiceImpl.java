package com.oiseau.ai_agent_tester.service.impl;

import com.oiseau.ai_agent_tester.model.Project;
import com.oiseau.ai_agent_tester.repository.ProjectRepository;
import com.oiseau.ai_agent_tester.response.ProjectExecutionResponse;
import com.oiseau.ai_agent_tester.responses.ResponseExecution;
import com.oiseau.ai_agent_tester.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private SequencialProject sequencialProject;


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
        return projectRepository.save(project);
    }

    @Override
    public ProjectExecutionResponse runProject(int id, String input) {

        List<ResponseExecution> responses = new ArrayList<>();
        
        getProjectById(id).ifPresent(project -> {
            List<ResponseExecution> result = this.sequencialProject.run("Ola", project.getAgentSequences());
            responses.addAll(result);
        });

        return new ProjectExecutionResponse(input, responses);
    }



}