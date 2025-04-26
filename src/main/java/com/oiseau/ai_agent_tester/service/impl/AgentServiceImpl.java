package com.oiseau.ai_agent_tester.service.impl;

import com.oiseau.ai_agent_tester.model.NaturalLanguageAgent;
import com.oiseau.ai_agent_tester.repository.AgentRepository;
import com.oiseau.ai_agent_tester.service.AgentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Override
    public List<NaturalLanguageAgent> getAllAgents() {
        return agentRepository.findAll();
    }

    @Override
    public NaturalLanguageAgent createAgent(NaturalLanguageAgent agent) {
        return agentRepository.save(agent);
    }

}