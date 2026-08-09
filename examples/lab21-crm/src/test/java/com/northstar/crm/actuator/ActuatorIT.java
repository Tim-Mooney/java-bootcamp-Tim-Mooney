package com.northstar.crm.actuator;

import com.northstar.crm.health.CrmReadinessIndicator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Map;
import org.springframework.http.*;
import com.northstar.crm.model.Customer;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ActuatorIT {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate rest;

    @Autowired
    CrmReadinessIndicator readiness;

    @Test
    void healthIsUp() {
        var res = rest.getForEntity("http://localhost:" + port + "/actuator/health", Map.class);
        assertThat(res.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(res.getBody().get("status")).isEqualTo("UP");
    }
    @Test
    void healthAndProbesAreUp() {
        ResponseEntity<Map> health = rest.getForEntity("http://localhost:" + port + "/actuator/health", Map.class);
        assertTrue(health.getStatusCode().is2xxSuccessful());
        assertEquals("UP", health.getBody().get("status"));

        ResponseEntity<Map> live = rest.getForEntity("http://localhost:" + port + "/actuator/health/liveness", Map.class);
        assertTrue(live.getStatusCode().is2xxSuccessful());
        assertEquals("UP", live.getBody().get("status"));

        ResponseEntity<Map> ready = rest.getForEntity("http://localhost:" + port + "/actuator/health/readiness", Map.class);
        assertTrue(ready.getStatusCode().is2xxSuccessful());
        assertEquals("UP", ready.getBody().get("status"));
    }


    @Test
    void readinessCanGoDownWhileLivenessStaysUp() {
        try{
            readiness.setReady(false);
            ResponseEntity<Map> ready = rest.getForEntity("http://localhost:" + port + "/actuator/health/readiness", Map.class);
            assertFalse(ready.getStatusCode().is2xxSuccessful()
                    && "UP".equals(ready.getBody() != null ? ready.getBody().get("status") : null));

            ResponseEntity<Map> live = rest.getForEntity("http://localhost:" + port + "/actuator/health/liveness", Map.class);
            assertTrue(live.getStatusCode().is2xxSuccessful());
            assertEquals("UP", live.getBody().get("status"));
        }finally{
            readiness.setReady(true);
        }
    }

    @Test
    void createMetricAppearsAfterTraffic() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Correlation-Id", "lab-request-001");
        headers.setContentType(MediaType.APPLICATION_JSON);
        Customer body = new Customer("CUS-1003", "Metric User", "metric@example.com", "PROSPECT");
        ResponseEntity<Customer> created = rest.exchange(
                "http://localhost:" + port + "/api/customers",
                HttpMethod.POST,
                new HttpEntity<>(body, headers),
                Customer.class);
        assertEquals(HttpStatus.CREATED, created.getStatusCode());

        rest.getForEntity("http://localhost:" + port + "/api/customers/CUS-1001", Customer.class);

        ResponseEntity<String> metric = rest.getForEntity(
        "http://localhost:" + port + "/actuator/metrics/crm.customer.create", String.class);
        assertTrue(metric.getStatusCode().is2xxSuccessful(), () -> "metric status=" + metric.getStatusCode());
        assertNotNull(metric.getBody());
        assertTrue(metric.getBody().contains("crm.customer.create")
                        || metric.getBody().contains("\"name\":\"crm.customer.create\""),
                () -> "unexpected metric body: " + metric.getBody());
    }
}
