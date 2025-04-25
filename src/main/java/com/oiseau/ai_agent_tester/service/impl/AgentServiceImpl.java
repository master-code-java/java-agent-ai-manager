package com.oiseau.ai_agent_tester.service.impl;

import com.oiseau.ai_agent_tester.model.Agent;
import com.oiseau.ai_agent_tester.repository.AgentRepository;
import com.oiseau.ai_agent_tester.service.AgentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Agent createAgent(Agent agent) {
        agent.setUuid(UUID.randomUUID().toString());
        return agentRepository.save(agent);
    }

}