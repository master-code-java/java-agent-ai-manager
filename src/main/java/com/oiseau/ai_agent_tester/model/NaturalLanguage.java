package com.oiseau.ai_agent_tester.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
@Entity
public abstract class NaturalLanguage extends Agent {

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(nullable = false)
	protected String context;
	
}