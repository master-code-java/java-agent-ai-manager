package com.oiseau.ai_agent_tester.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.oiseau.ai_agent_tester.responses.ResponseExecution;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "UUID", "initialInput", "responses" })
public class ProjectExecutionResponse {

    String initialInput;
    List<ResponseExecution> responses;
    
}