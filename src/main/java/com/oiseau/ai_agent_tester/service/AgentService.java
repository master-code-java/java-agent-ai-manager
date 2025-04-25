
package com.oiseau.ai_agent_tester.service;

import com.oiseau.ai_agent_tester.model.Agent;

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
    List<Agent> getAllAgents();

    Agent createAgent(Agent agent);

    }