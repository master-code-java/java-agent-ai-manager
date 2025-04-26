package com.oiseau.ai_agent_tester.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class NaturalLanguageAgent extends NaturalLanguage {                    

    @CreationTimestamp
    @Schema(hidden = true)
    @Column(updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Schema(hidden = true)
    private LocalDateTime updatedAt;

    public NaturalLanguageAgent() {
        this.type = "NaturalLanguage";
    }

   }
