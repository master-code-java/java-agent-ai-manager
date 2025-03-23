package com.oiseau.ai_agent_tester.repository;

import com.oiseau.ai_agent_tester.model.Agent;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    Optional<Agent> findByUuid(String uuid);
    List<Agent> findAllByUuidIn(List<String> uuids);
}