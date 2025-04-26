package com.oiseau.ai_agent_tester.service;

import java.util.List;

import com.oiseau.ai_agent_tester.model.AgentSequence;
import com.oiseau.ai_agent_tester.responses.ResponseExecution;

public interface SequencialExecution extends Execution {
    List<ResponseExecution> run(String input, List<AgentSequence> sequence); // Run the sequence of agents with the given input and return the responses

}