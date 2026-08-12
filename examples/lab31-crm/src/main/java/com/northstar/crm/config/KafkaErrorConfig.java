package com.northstar.crm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;
import com.northstar.crm.exception.UnsupportedEventVesionException;
import com.northstar.crm.exception.InvalidCustomerEventException;


@Configuration
public class KafkaErrorConfig {

  @Bean
  public CommonErrorHandler kafkaErrorHandler(KafkaTemplate<Object, Object> template) {
    var recoverer = new DeadLetterPublishingRecoverer(template);
    var handler = new DefaultErrorHandler(recoverer, new FixedBackOff(1000, 2));
    handler.addNotRetryableExceptions(
            InvalidCustomerEventException.class,
            UnsupportedEventVersionException.class);
    return handler;
  }
}
