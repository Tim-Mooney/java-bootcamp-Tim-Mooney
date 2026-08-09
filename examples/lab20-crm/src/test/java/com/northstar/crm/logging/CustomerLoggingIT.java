package com.northstar.crm.logging;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(OutputCaptureExtension.class)
class CustomerLoggingIT {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate rest;

    @Test
    void createLogsIdsNotPii(CapturedOutput output) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Correlation-Id", "lab-request-001");

        String requestBody = """
        {
          "customerId": "CUS-1001",
          "fullName": "Amina Khan",
          "email": "amina.khan@example.com"
        }
        """;

        ResponseEntity<String> res = rest.exchange(
                "http://localhost:" + port + "/api/customers",
                HttpMethod.POST,
                new HttpEntity<>(requestBody, headers),
                String.class);

        assertThat(output.getOut()).contains("CUS-1001");
        assertThat(output.getOut()).contains("lab-request-001");
        assertThat(output.getOut()).contains("customer.create");
        assertThat(output.getOut()).doesNotContain("Amina");
    }

    @Test
    void getAminaLogsCorrelationWithoutPii(CapturedOutput output) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Correlation-Id", "lab-request-001");
        ResponseEntity<String> res = rest.exchange(
                "http://localhost:" + port + "/api/customers/CUS-1001",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class);
        assertEquals(HttpStatus.OK, res.getStatusCode());

        String logs = output.getOut() + output.getErr();
        assertTrue(logs.contains("lab-request-001"));
        assertTrue(logs.contains("CUS-1001"));
        assertFalse(logs.contains("Amina"));
        assertFalse(logs.toLowerCase().contains("amina.khan@example.com"));
    }

    @Test
    void noNameHasWarn(CapturedOutput output) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Correlation-Id", "lab-request-001");

        String requestBody = """
         { "customerId": "CUS-1002", "email": "ravi.singh@example.com", "status": "PROSPECT" }
        """;

        ResponseEntity<String> res = rest.exchange(
                "http://localhost:" + port + "/api/customers",
                HttpMethod.POST,
                new HttpEntity<>(requestBody, headers),
                String.class);

        assertThat(output.getOut()).contains("WARN");
    }

}