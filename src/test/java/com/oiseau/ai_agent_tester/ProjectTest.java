package com.oiseau.ai_agent_tester;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import com.oiseau.ai_agent_tester.interfaces.NaturalLanguage;
import com.oiseau.ai_agent_tester.interfaces.SequencialExecution;
import com.oiseau.ai_agent_tester.responses.ResponseExecution;
import com.oiseau.ai_agent_tester.model.AgentSequence;

class NaturalLanguageAgent implements NaturalLanguage{

	private final String NATURAL_LANGUAGE = "NaturalLanguage";
	private String context;

	@Override
	public void context(String context) {
		this.context = context;
	
	}
	
	@Override
	public String getContext() {
		return this.context;

	}		

	@Override
	public String getType() {
		return this.NATURAL_LANGUAGE;
	
	}
}

class MyProject implements SequencialExecution{

	/* for mock purpose only */
	OpenAiService openAiService = new OpenAiService();

	public List<ResponseExecution> run(String input, List<AgentSequence> sequence){
		List<ResponseExecution> responseExecutions = new ArrayList<>();
		String[] currentInput = { input };

		sequence.stream()
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

class ProjectTest {

	@Test
	void givenOrderedSequence_whenRunProject_thenRunOrderedSequence(){
		NaturalLanguage nlp1 = new NaturalLanguageAgent();
		nlp1.context("Voce traduz para Arabe");

		NaturalLanguage nlp2 = new NaturalLanguageAgent();
		nlp2.context("Voce traduz para Ingles");

		NaturalLanguage nlp3 = new NaturalLanguageAgent();
		nlp3.context("Voce traduz para Frances");
		
		List<AgentSequence> sequence = new ArrayList<>();
		sequence.add(new AgentSequence(1, nlp1));
		sequence.add(new AgentSequence(2,nlp2));
		sequence.add(new AgentSequence(3, nlp3));
		
		MyProject p1 = new MyProject();
		List<ResponseExecution> result = p1.run("Ola", sequence);

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
		NaturalLanguage nlp1 = new NaturalLanguageAgent();
		nlp1.context("Voce traduz para Arabe");

		NaturalLanguage nlp2 = new NaturalLanguageAgent();
		nlp2.context("Voce traduz para Ingles");

		NaturalLanguage nlp3 = new NaturalLanguageAgent();
		nlp3.context("Voce traduz para Frances");
		
		List<AgentSequence> sequence = new ArrayList<>();
		sequence.add(new AgentSequence(3, nlp3));
		sequence.add(new AgentSequence(1,nlp1));
		sequence.add(new AgentSequence(2, nlp2));

		MyProject p1 = new MyProject();
		List<ResponseExecution> result = p1.run("Ola", sequence);

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
