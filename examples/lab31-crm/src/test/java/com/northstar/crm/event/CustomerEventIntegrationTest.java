package com.northstar.crm.event;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = {"crm.customer-events.v1"})
@TestPropertySource(properties = {
    "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}",
    "crm.kafka.customer-events-topic=crm.customer-events.v1"
})
class CustomerEventIntegrationTest {

  @Test
  void contextLoads() {
    publisher.publish(createdEvent); // CUS-1001 / lab-request-001
    await().atMost(Duration.ofSeconds(10)).untilAsserted(() ->
            assertThat(handler.events()).extracting(CustomerEvent::eventId)
                    .contains(createdEvent.eventId()));
  }
}
