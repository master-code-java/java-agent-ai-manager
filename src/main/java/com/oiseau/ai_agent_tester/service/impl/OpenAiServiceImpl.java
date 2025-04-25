package com.oiseau.ai_agent_tester.service.impl;

import com.oiseau.ai_agent_tester.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class OpenAiServiceImpl implements OpenAiService {

    private static final Logger logger = LogManager.getLogger(OpenAiServiceImpl.class);

    @Value("${openai.api.url}")
    private String apiUrl;

    private final String apiKey;
    private final RestTemplate restTemplate;

    public OpenAiServiceImpl(RestTemplate restTemplate, String openAiApiKey) {
        this.restTemplate = restTemplate;
        this.apiKey = openAiApiKey;
    }

    @Override
    public String getResponse(String context, String input) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        String requestBody = String.format(
            "{\"max_tokens\": 100, \"model\": \"gpt-3.5-turbo\", \"messages\": [" +
            "{\"role\": \"system\", \"content\": \"%s\"}," +
            "{\"role\": \"user\", \"content\": \"%s\"}]}",
            context,
            input
        );

        // Log do contexto e input enviados
        logger.info("Sending request to OpenAI API");
        logger.info("Context: {}", context);
        logger.info("Input: {}", input);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);

        // Log da resposta recebida
        logger.info("Response received from OpenAI API: {}", response.getBody());

        return response.getBody();
    }
}