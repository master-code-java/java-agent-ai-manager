package com.oiseau.ai_agent_tester.service;


/**
 * Service interface for interacting with OpenAI to get personalized responses.
 */
public interface OpenAiService {

    String getResponse(String context, String input);

}
