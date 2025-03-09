package com.oiseau.ai_agent_tester.repository;

import com.oiseau.ai_agent_tester.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    Agent findByUuid(UUID uuid);
}