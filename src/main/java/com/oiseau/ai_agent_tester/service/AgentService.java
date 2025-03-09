
package com.oiseau.ai_agent_tester.service;

import com.oiseau.ai_agent_tester.model.Agent;

import java.util.List;
import java.util.Optional;

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

    /**
     * Retrieves an agent by its ID.
     *
     * @param id the ID of the agent to retrieve
     * @return an Optional containing the agent if found, or empty if not found
     */
    Optional<Agent> getAgentById(Long id);

    /**
     * Creates a new agent.
     *
     * @param agent the agent to create
     * @return the created agent
     */
    Agent createAgent(Agent agent);

    /**
     * Updates an existing agent.
     *
     * @param id the ID of the agent to update
     * @param agentDetails the details to update the agent with
     * @return an Optional containing the updated agent if the update was successful, or empty if not
     */
    Optional<Agent> updateAgent(Long id, Agent agentDetails);

    /**
     * Deletes an agent by its ID.
     *
     * @param id the ID of the agent to delete
     * @return true if the agent was successfully deleted, false otherwise
     */
    boolean deleteAgent(Long id);
}