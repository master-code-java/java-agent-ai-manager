package com.oiseau.ai_agent_tester.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;

import java.time.LocalDateTime;
import java.util.UUID;

import com.oiseau.ai_agent_tester.interfaces.NaturalLanguage;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Agent implements NaturalLanguage {

    private static final String NATURAL_LANGUAGE = "NaturalLanguage";
    private String context;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private UUID uuid;
        
    @Column(nullable = false)
    private String name;
    
    @CreationTimestamp
    @Schema(hidden = true)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Schema(hidden = true)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
        }
    }

    @Override
    public void context(String context) {
        this.context = context;
    
    }

    @Override
    public String getType() {
       return NATURAL_LANGUAGE;
    
    }

    @Override
    public String getContext() {
        return this.context;
    
    }

    @Override
    public void context(String context) {
        this.context = context;
    
    }

    @Override
    public String getType() {
       return NATURAL_LANGUAGE;
    
    }

    @Override
    public String getContext() {
        return this.context;
    
    }
}
