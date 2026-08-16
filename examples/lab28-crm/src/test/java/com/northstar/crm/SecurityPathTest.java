package com.northstar.crm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityPathTest {

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
  void missingTokenIs401() throws Exception {
    mockMvc.perform(get("/api/customers/CUS-1001"))
            .andExpect(status().isUnauthorized());
  }

  @Test
  void agentCanReadCustomerButNotAdmin() throws Exception {
    String token = login("agent1", "agent1");

    mockMvc.perform(get("/api/customers/CUS-1001")
                    .header("Authorization", "Bearer " + token))
            .andExpect(status().isOk());

    mockMvc.perform(get("/api/admin/ping")
                    .header("Authorization", "Bearer " + token))
            .andExpect(status().isForbidden());
  }

  @Test
  void adminCanPing() throws Exception {
    String token = login("admin1", "admin1");

    mockMvc.perform(get("/api/admin/ping")
                    .header("Authorization", "Bearer " + token))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.role").value("ADMIN"));
  }
}