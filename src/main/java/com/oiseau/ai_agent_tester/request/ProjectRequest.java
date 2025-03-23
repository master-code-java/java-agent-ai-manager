package com.oiseau.ai_agent_tester.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequest {

    private String name;

    private List<String> projectsUUID = new ArrayList<>();
}
