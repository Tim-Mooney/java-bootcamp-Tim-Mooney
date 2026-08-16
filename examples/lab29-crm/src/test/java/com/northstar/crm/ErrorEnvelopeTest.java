package com.northstar.crm;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ErrorEnvelopeTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  private String login(String username, String password) throws Exception {
    MvcResult result = mockMvc.perform(post("/api/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(
                            Map.of("username", username, "password", password))))
            .andExpect(status().isOk())
            .andReturn();

    String body = result.getResponse().getContentAsString();
    return objectMapper.readTree(body).get("accessToken").asText();
  }

  @Test
  void validationReturns400Envelope() throws Exception {
    String token = login("agent1", "agent1");

    String invalidCustomerJson = """
        {"id":"CUS-2001","name":"Test User","email":"not-an-email","status":"ACTIVE"}
        """;

    mockMvc.perform(post("/api/customers")
                    .header("Authorization", "Bearer " + token)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(invalidCustomerJson))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.status").value(400))
            .andExpect(jsonPath("$.violations").isArray())
            .andExpect(jsonPath("$.violations").isNotEmpty());
  }

  @Test
  void missingCustomerReturns404Envelope() throws Exception {
    String token = login("agent1", "agent1");

    mockMvc.perform(get("/api/customers/CUS-9999")
                    .header("Authorization", "Bearer " + token))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status").value(404))
            .andExpect(jsonPath("$.error").value("Not Found"));
  }

  @Test
  void duplicateReturns409Envelope() throws Exception {
    String token = login("agent1", "agent1");

    String duplicateCustomerJson = """
        {"id":"CUS-1001","name":"Amina Khan","email":"amina.khan@example.com","status":"ACTIVE"}
        """;

    mockMvc.perform(post("/api/customers")
                    .header("Authorization", "Bearer " + token)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(duplicateCustomerJson))
            .andExpect(status().isConflict())
            .andExpect(jsonPath("$.status").value(409))
            .andExpect(jsonPath("$.error").value("Conflict"));
  }

  @Test
  void securityStillRequiresToken() throws Exception {
    mockMvc.perform(get("/api/customers/CUS-1001"))
            .andExpect(status().isUnauthorized());
  }
}