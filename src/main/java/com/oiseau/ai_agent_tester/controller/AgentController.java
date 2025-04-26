package com.oiseau.ai_agent_tester.controller;

import com.oiseau.ai_agent_tester.model.NaturalLanguageAgent;
import com.oiseau.ai_agent_tester.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agents")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @GetMapping
    public List<NaturalLanguageAgent> getAllAgents() {
        return agentService.getAllAgents();
    }

    @PostMapping
    public NaturalLanguageAgent createAgent(@RequestBody NaturalLanguageAgent agent) {
        return agentService.createAgent(agent);
    }

}