package com.northstar.crm.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.TestPropertySource;

import java.time.Duration;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = {"crm.customer-events.v1"})
@TestPropertySource(properties = {
        "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}",
        "crm.kafka.customer-events-topic=crm.customer-events.v1"
})
class CustomerEventIntegrationTest {

  @Autowired
  private CustomerEventPublisher publisher;

  @Autowired
  private ProcessedEventStore store;

  private CustomerEvent createdEvent;

  @BeforeEach
  void setUp() {
    createdEvent = new CustomerEvent(
            "lab-request-001",
            "CustomerCreated",
            1,
            Instant.now(),
            "CUS-1001",
            "corr-001",
            "crm-test",
            new CustomerEvent.CustomerData("Amina Yusuf", "ACTIVE")
    );
  }

  @Test
  void publishesAndConsumesCustomerCreated() {
    publisher.publish(createdEvent);

    await().atMost(Duration.ofSeconds(10)).untilAsserted(() ->
            assertThat(store.markIfNew(createdEvent.eventId())).isTrue());
  }
}