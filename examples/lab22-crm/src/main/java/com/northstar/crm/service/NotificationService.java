package com.northstar.crm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



@Service
public class NotificationService {
  private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

  public void notifyCreated(String customerId, String correlationId) {
    try {
      Thread.sleep(10000);
      log.info("customer.created id={} correlationId={}", customerId, correlationId);
    }
    catch(InterruptedException e){
      log.info("no notify");
    }
  }
}
