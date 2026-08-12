package com.northstar.crm.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import com.northstar.crm.config.KafkaErrorConfig;//could be totally wrong.
import com.northstar.crm.exception.InvalidCustomerEventException;


@Component
public class CustomerEventListener {

  private static final Logger log = LoggerFactory.getLogger(CustomerEventListener.class);
  private final ProcessedEventStore store;
  //private KafkaErrorConfig handler = new KafkaErrorConfig();

  public CustomerEventListener(ProcessedEventStore store) {
    this.store = store;
  }

  @KafkaListener(topics = "${crm.kafka.customer-events-topic}")
  public void onCustomerEvent(
      @Payload CustomerEvent event,
      @Header(KafkaHeaders.RECEIVED_KEY) String key) {
    if (key == null || !key.equals(event.customerId())) {
      throw new InvalidCustomerEventException("key mismatch");
    }
    if(!store.markIfNew(event.eventId())){
      log.info("duplicate_event_ignored id={}", event.eventId());
      return;
    }
    log.info("CorrelationId = "+ event.correlationId() + " customerId = "+ event.customerId());
    handler.handle(event);
    // TODO: reject when key == null or key does not equal event.customerId()
    // TODO: skip when !store.markIfNew(event.eventId())
    // TODO: log correlationId + customerId (no PII beyond fixture ids)
    //throw new UnsupportedOperationException("TODO: validate key, idempotency, handle");
  }
}
