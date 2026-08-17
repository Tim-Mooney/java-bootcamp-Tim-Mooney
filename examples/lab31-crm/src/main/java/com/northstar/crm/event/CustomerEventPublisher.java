package com.northstar.crm.event;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; //maybe

@Component
public class CustomerEventPublisher {
    //maybe no logger we will se
  private static final Logger log = LoggerFactory.getLogger(CustomerEventListener.class);
  private final KafkaTemplate<String, CustomerEvent> kafkaTemplate;
  private final String topic;

  public CustomerEventPublisher(
      KafkaTemplate<String, CustomerEvent> kafkaTemplate,
      @Value("${crm.kafka.customer-events-topic}") String topic) {
    this.kafkaTemplate = kafkaTemplate;
    this.topic = topic;
  }

  public void publish(CustomerEvent event) {
      kafkaTemplate.send(topic, event.customerId(), event)
            .whenComplete((result, error) -> {
              if (error != null)
                log.error("customer_event_publish_failed id={}", event.eventId(), error);
              else
                log.info("customer_event_published id={} partition={} offset={}",
                        event.eventId(),
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
            });
  }
}
