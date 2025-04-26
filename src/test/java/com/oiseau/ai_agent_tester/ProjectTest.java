package com.oiseau.ai_agent_tester;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import com.oiseau.ai_agent_tester.responses.ResponseExecution;
import com.oiseau.ai_agent_tester.service.impl.SequencialProject;
import com.oiseau.ai_agent_tester.model.AgentSequence;
import com.oiseau.ai_agent_tester.model.NaturalLanguage;
import com.oiseau.ai_agent_tester.model.NaturalLanguageAgent;

class ProjectTest {

	@Test
	void givenOrderedSequence_whenRunProject_thenRunOrderedSequence(){
		NaturalLanguage nlp1 = new NaturalLanguageAgent();
		nlp1.setContext("Voce traduz para Arabe");

		NaturalLanguage nlp2 = new NaturalLanguageAgent();
		nlp2.setContext("Voce traduz para Ingles");

		NaturalLanguage nlp3 = new NaturalLanguageAgent();
		nlp3.setContext("Voce traduz para Frances");
		
		List<AgentSequence> sequence = new ArrayList<>();
	
		AgentSequence sq1 =  new AgentSequence();
		sq1.setPosition(1);
		sq1.setAgent(nlp1);
		sequence.add(sq1);
		
		AgentSequence sq2 =  new AgentSequence();
		sq2.setPosition(2);
		sq2.setAgent(nlp2);
		sequence.add(sq2);	

		AgentSequence sq3 =  new AgentSequence();	
		sq3.setPosition(3);
		sq3.setAgent(nlp3);
		sequence.add(sq3);

		List<ResponseExecution> result = new SequencialProject((String context, String input) -> {
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
		}).run("Ola", sequence);

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
		nlp1.setContext("Voce traduz para Arabe");

		NaturalLanguage nlp2 = new NaturalLanguageAgent();
		nlp2.setContext("Voce traduz para Ingles");

		NaturalLanguage nlp3 = new NaturalLanguageAgent();
		nlp3.setContext("Voce traduz para Frances");
		
		List<AgentSequence> sequence = new ArrayList<>();

		AgentSequence sq1 =  new AgentSequence();
		sq1.setPosition(3);
		sq1.setAgent(nlp3);
		sequence.add(sq1);
		
		AgentSequence sq2 =  new AgentSequence();
		sq2.setPosition(1);
		sq2.setAgent(nlp1);
		sequence.add(sq2);	

		AgentSequence sq3 =  new AgentSequence();	
		sq3.setPosition(2);
		sq3.setAgent(nlp2);
		sequence.add(sq3);

		List<ResponseExecution> result = new SequencialProject((String context, String input) -> {
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
		}).run("Ola", sequence);

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
