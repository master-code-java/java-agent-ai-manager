package com.oiseau.ai_agent_tester.service;

import com.oiseau.ai_agent_tester.model.Agent;

/**
 * Service interface for interacting with OpenAI to get personalized responses.
 */
public interface OpenAiService {

    String getPersonalizedResponse(Agent agent);
}
