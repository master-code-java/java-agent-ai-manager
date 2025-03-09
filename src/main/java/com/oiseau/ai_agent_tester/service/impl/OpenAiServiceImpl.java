package com.oiseau.ai_agent_tester.service.impl;

import com.oiseau.ai_agent_tester.model.Agent;
import com.oiseau.ai_agent_tester.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service
public class OpenAiServiceImpl implements OpenAiService {

    @Value("${openai.api.url}")
    private String apiUrl;

    private final String apiKey;
    private final RestTemplate restTemplate;

    public OpenAiServiceImpl(RestTemplate restTemplate, String openAiApiKey) {
        this.restTemplate = restTemplate;
        this.apiKey = openAiApiKey;
    }

    @Override
    public String getPersonalizedResponse(Agent agent) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        String requestBody = String.format(
            "{\"max_tokens\": 100, \"model\": \"gpt-3.5-turbo\", \"messages\": [" +
            "{\"role\": \"system\", \"content\": \"%s\"}," +
            "{\"role\": \"user\", \"content\": \"%s\"}]}",
            agent.getContext(),
            agent.getDescription()
        );
        

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }
}