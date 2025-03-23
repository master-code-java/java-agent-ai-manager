package com.oiseau.ai_agent_tester.service.impl;

import com.oiseau.ai_agent_tester.model.Agent;
import com.oiseau.ai_agent_tester.repository.AgentRepository;
import com.oiseau.ai_agent_tester.service.AgentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Override
    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    @Override
    public Optional<Agent> getAgentById(Long id) {
        return agentRepository.findById(id);
    }

    @Override
    public Agent createAgent(Agent agent) {
        agent.setUuid(UUID.randomUUID().toString());
        return agentRepository.save(agent);
    }

    @Override
    public Optional<Agent> updateAgent(Long id, Agent agentDetails) {
        return agentRepository.findById(id).map(agent -> {
            agent.setName(agentDetails.getName());
            agent.setContext(agentDetails.getContext());
            agent.setDescription(agentDetails.getDescription());
            return agentRepository.save(agent);
        });
    }

    @Override
    public boolean deleteAgent(Long id) {
        if (agentRepository.existsById(id)) {
            agentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}