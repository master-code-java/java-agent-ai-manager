package com.oiseau.ai_agent_tester.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oiseau.ai_agent_tester.interfaces.SequencialExecution;
import com.oiseau.ai_agent_tester.model.AgentSequence;
import com.oiseau.ai_agent_tester.model.NaturalLanguage;
import com.oiseau.ai_agent_tester.responses.ResponseExecution;
import com.oiseau.ai_agent_tester.service.OpenAiService;

@Service
public class SequencialProject implements SequencialExecution{
    /* for mock purpose only */

	@Autowired
	private OpenAiService openAiService;

	public SequencialProject(OpenAiService openAiService){
		this.openAiService = openAiService;
	}	
	
	

	public List<ResponseExecution> run(String input, List<AgentSequence> sequence){
		List<ResponseExecution> responseExecutions = new ArrayList<>();
		String[] currentInput = { input };

		sequence.stream()
			.sorted((o1, o2) -> Integer.compare(o1.getPosition(), o2.getPosition()))
			.forEachOrdered(agentSequence -> {			
				String agentContext = ((NaturalLanguage) agentSequence.getAgent()).getContext();
				String[] agentResponse = {extractContentFromResponse(openAiService.getResponse(agentContext, currentInput[0]))};
				responseExecutions.add(new ResponseExecution(agentSequence.getPosition(), agentContext, currentInput[0], agentResponse[0]));
				currentInput[0] = agentResponse[0];
			});

		System.out.println("ResponseExecutions: " + responseExecutions);
		return responseExecutions;

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
