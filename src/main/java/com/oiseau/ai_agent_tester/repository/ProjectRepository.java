package com.oiseau.ai_agent_tester.repository;

import com.oiseau.ai_agent_tester.model.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

}