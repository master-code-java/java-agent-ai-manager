package com.oiseau.ai_agent_tester.responses;

import org.springframework.stereotype.Component;

@Component
public class ResponseExecution{
	private int position;
	private String context;
	private String input;
	private String response;

	public ResponseExecution(int position, String context, String input, String response){
		this.position = position;
		this.context = context;
		this.input = input;
		this.response = response;
	}

	public int getPosition() {
		return position;
	}

	public String getContext() {
		return context;
	}

	public String getInput() {
		return input;
	}

	public String getResponse() {
		return response;
	}

	@Override
	public String toString() {
		return "ResponseExecution [position=" + position + ", context=" + context + ", input=" + input + ", response=" + response + "]";
	}
}