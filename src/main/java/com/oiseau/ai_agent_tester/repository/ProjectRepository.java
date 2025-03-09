package com.oiseau.ai_agent_tester.repository;

import com.oiseau.ai_agent_tester.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Project findByUuid(UUID uuid);
}