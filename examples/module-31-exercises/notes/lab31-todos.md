# Lab 31 — Fill Spring Kafka TODOs


```java
// application.yml ideas
spring.kafka.bootstrap-servers: localhost:9092
spring.kafka.consumer.group-id: crm-notifications

@Service
class CustomerEventPublisher {
  private final KafkaTemplate<String, String> template;
  void publishCreated(String customerId, String json) {
    template.send("crm.customer-events.v1", customerId, json); // topic
  }
}

@KafkaListener(topics = "crm.customer-events.v1", groupId = "crm-notifications")
void onEvent(String payload) { /* TODO: parse + idempotent handle */ }
// TODO Lab 31: route poison messages to crm.customer-events.v1.dlq.
```