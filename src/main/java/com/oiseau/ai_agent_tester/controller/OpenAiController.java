package com.oiseau.ai_agent_tester.controller;

import com.oiseau.ai_agent_tester.service.AgentService;
import com.oiseau.ai_agent_tester.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/openai")
public class OpenAiController {

    @Autowired
    private OpenAiService openAiService;

    @Autowired
    private AgentService agentService;

    @GetMapping("/response/{agentId}")
    public ResponseEntity<String> getPersonalizedResponse(@PathVariable Long agentId) {
        return agentService.getAgentById(agentId)
                .map(agent -> ResponseEntity.ok(openAiService.getPersonalizedResponse(agent)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}