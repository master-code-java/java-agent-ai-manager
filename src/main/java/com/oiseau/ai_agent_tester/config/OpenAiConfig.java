package com.oiseau.ai_agent_tester.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenAiConfig {

    @Bean
    public String openAiApiKey() {
        String apiKey = System.getenv("OPENAI_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalStateException("OpenAI API key is not set in the environment variables");
        }
        return apiKey;
    }

     @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}