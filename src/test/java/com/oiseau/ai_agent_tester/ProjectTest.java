package com.oiseau.ai_agent_tester;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;;

class Agent{

	private String context;

	Agent(String context){
		this.context = context;
	}

	public String getContext() {
		return context;
	}

}

class Project{

	private String input;
	private List<Agent> agents = new ArrayList<>();
	private List<AgentSequence> sequence = new ArrayList<>();

	OpenAiService openAiService = new OpenAiService();


	Project(String input, List<Agent> agents, List<AgentSequence> sequence){
		this.agents = agents;
		this.input = input;
		this.sequence = sequence;
	}

	public String getInput() {
		return input;
	}

	public List<Agent> getAgents() {
		return agents;
	}

	public List<AgentSequence> getSequence() {
		return sequence;
	}

	public List<ResponseExecution> run(){
		List<ResponseExecution> responseExecutions = new ArrayList<>();
		String[] currentInput = { this.input };

		this.sequence.stream()
			.sorted((o1, o2) -> Integer.compare(o1.getPosition(), o2.getPosition()))
			.forEachOrdered(agentSequence -> {			
				String[] agentResponse = {openAiService.getResponse(agentSequence.getAgent().getContext(), currentInput[0])};
				responseExecutions.add(new ResponseExecution(agentSequence.getPosition(), agentSequence.getAgent().getContext(), currentInput[0], agentResponse[0]));
				currentInput[0] = agentResponse[0];
			});

		System.out.println("ResponseExecutions: " + responseExecutions);
		return responseExecutions;

	}

}

class ResponseExecution{
	private int position;
	private String context;
	private String input;
	private String response;

	ResponseExecution(int position, String context, String input, String response){
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

class OpenAiService{
	public String getResponse(String context, String input){
		if(context.equals("Voce traduz para Arabe") && input.equals("Ola")){
			return "Ola em Arabe";
		}

		if(context.equals("Voce traduz para Ingles") && input.equals("Ola em Arabe")){
			return "Ola! em ingles";
		}

		if(context.equals("Voce traduz para Frances") && input.equals("Ola! em ingles")){
			return "Ola! em frances";
		}
		
		return "";

	}
}

class AgentSequence{
	
	private int position;
	private Agent agent;

	AgentSequence(int position, Agent agent){
		this.position = position;
		this.agent = agent;
	}
	public int getPosition() {
		return position;
	}

	public Agent getAgent() {
		return agent;
	}

	@Override
	public String toString() {
		return "AgentSequence [position=" + position + ", agent=" + agent.getContext() + "]";
	}

	
}

class ProjectTest {

	@Test
	void givenOrderedSequence_whenRunProject_thenRunOrderedSequence(){
		Agent a1 = new Agent("Voce traduz para Arabe");
		Agent a2 = new Agent("Voce traduz para Ingles");
		Agent a3 = new Agent("Voce traduz para Frances");

		List<Agent> agents = new ArrayList<>();
		agents.add(a1);		
		agents.add(a2);
		agents.add(a3);

		List<AgentSequence> sequence = new ArrayList<>();
		sequence.add(new AgentSequence(1, a1));
		sequence.add(new AgentSequence(2,a2));
		sequence.add(new AgentSequence(3, a3));
		
		Project p1 = new Project("Ola", agents, sequence);

		List<ResponseExecution> result = p1.run();

		assertAll("", () -> {
			assert result.get(0).getPosition() == 1;
			assert result.get(1).getPosition() == 2;
			assert result.get(2).getPosition() == 3;

			assert result.get(0).getContext().equals("Voce traduz para Arabe");
			assert result.get(1).getContext().equals("Voce traduz para Ingles");
			assert result.get(2).getContext().equals("Voce traduz para Frances");

			assert result.get(0).getInput().equals("Ola");
			assert result.get(1).getInput().equals("Ola em Arabe");
			assert result.get(2).getInput().equals("Ola! em ingles");
			
			assert result.get(0).getResponse().equals("Ola em Arabe");	
			assert result.get(1).getResponse().equals("Ola! em ingles");
			assert result.get(2).getResponse().equals("Ola! em frances");

		});
		

	}

	@Test
	void givenUnorderedSequence_whenRunProject_thenRunOrderedSequence(){
		Agent a1 = new Agent("Voce traduz para Arabe");
		Agent a2 = new Agent("Voce traduz para Ingles");;
		Agent a3 = new Agent("Voce traduz para Frances");

		List<Agent> agents = new ArrayList<>();
		agents.add(a1);		
		agents.add(a2);
		agents.add(a3);

		List<AgentSequence> sequence = new ArrayList<>();
		sequence.add(new AgentSequence(3, a3));
		sequence.add(new AgentSequence(2,a2));
		sequence.add(new AgentSequence(1, a1));

		Project p1 = new Project("Ola", agents, sequence);

		List<ResponseExecution> result = p1.run();

		assertAll("", () -> {
			assert result.get(0).getPosition() == 1;
			assert result.get(1).getPosition() == 2;
			assert result.get(2).getPosition() == 3;

			assert result.get(0).getContext().equals("Voce traduz para Arabe");
			assert result.get(1).getContext().equals("Voce traduz para Ingles");
			assert result.get(2).getContext().equals("Voce traduz para Frances");

			assert result.get(0).getInput().equals("Ola");
			assert result.get(1).getInput().equals("Ola em Arabe");
			assert result.get(2).getInput().equals("Ola! em ingles");
			
			assert result.get(0).getResponse().equals("Ola em Arabe");	
			assert result.get(1).getResponse().equals("Ola! em ingles");
			assert result.get(2).getResponse().equals("Ola! em frances");

		});

	}		

	@Test
	void givenUnorderedAgentsList_whenRunProject_thenRunOrderedSequence(){
		Agent a1 = new Agent("Voce traduz para Arabe");
		Agent a2 = new Agent("Voce traduz para Ingles");
		Agent a3 = new Agent("Voce traduz para Frances");

		List<Agent> agents = new ArrayList<>();
		agents.add(a3);		
		agents.add(a2);
		agents.add(a1);

		List<AgentSequence> sequence = new ArrayList<>();
		sequence.add(new AgentSequence(1, a1));
		sequence.add(new AgentSequence(2,a2));
		sequence.add(new AgentSequence(3, a3));

		Project p1 = new Project("Ola", agents, sequence);

		List<ResponseExecution> result = p1.run();

		assertAll("", () -> {
			assert result.get(0).getPosition() == 1;
			assert result.get(1).getPosition() == 2;
			assert result.get(2).getPosition() == 3;

			assert result.get(0).getContext().equals("Voce traduz para Arabe");
			assert result.get(1).getContext().equals("Voce traduz para Ingles");
			assert result.get(2).getContext().equals("Voce traduz para Frances");

			assert result.get(0).getInput().equals("Ola");
			assert result.get(1).getInput().equals("Ola em Arabe");
			assert result.get(2).getInput().equals("Ola! em ingles");
			
			assert result.get(0).getResponse().equals("Ola em Arabe");	
			assert result.get(1).getResponse().equals("Ola! em ingles");
			assert result.get(2).getResponse().equals("Ola! em frances");

		});
		
	}




}
