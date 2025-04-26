package com.oiseau.ai_agent_tester.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.InheritanceType;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED) 
@DiscriminatorColumn(name = "agent_type")
@Entity
public abstract class Agent {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;    

    @Column(nullable = false)
    protected String name;

    protected String description;

    @Column(nullable = false)
    protected String type;

}
