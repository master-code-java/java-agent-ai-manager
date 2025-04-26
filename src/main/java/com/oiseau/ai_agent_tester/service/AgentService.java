
package com.oiseau.ai_agent_tester.service;

import com.oiseau.ai_agent_tester.model.NaturalLanguageAgent;

import java.util.List;

/**
 * Service interface for managing agents.
 */
public interface AgentService {

    /**
     * Retrieves all agents.
     *
     * @return a list of all agents
     */
    List<NaturalLanguageAgent> getAllAgents();

    NaturalLanguageAgent createAgent(NaturalLanguageAgent agent);

    }