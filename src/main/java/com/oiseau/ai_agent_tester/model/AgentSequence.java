package com.oiseau.ai_agent_tester.model;
import com.oiseau.ai_agent_tester.interfaces.Agent;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;


@Entity
@Data
@AllArgsConstructor
public class AgentSequence{
	
	private int position;
	private Agent agent;

	@Override
	public String toString() {
		return "AgentSequence [position=" + position + ", agent=" + agent.getContext() + "]";
	}

	
}