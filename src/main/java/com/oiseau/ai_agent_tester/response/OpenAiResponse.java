package com.oiseau.ai_agent_tester.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "agentUuid", "context", "content" })
public class OpenAiResponse {
    private String agentUuid;
    private String context;
    private String content;
}