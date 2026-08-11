package com.northstar.crm.service;

import com.northstar.crm.model.Customer;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CustomerService {
  private final Map<String, Customer> store = new ConcurrentHashMap<>();
  private static final Logger log = LoggerFactory.getLogger(CustomerService.class);


  public CustomerService() {
    store.put("CUS-1001", Customer.amina());
    store.put("CUS-1002", Customer.ravi());
  }

  public Customer create(Customer customer, String correlationId) {
    // TODO: reject blank id; put into store; return customer (correlation for logs/evidence)
    if(customer.getId().isBlank()){
      log.info("Rejected blank id Correlation = " + correlationId);
      throw new IllegalArgumentException("Blank ID");
    }
    else{
      store.put(customer.getId(),customer);
      log.info("Created customer "+ customer.getId());
      return customer;
    }
  }

  public Customer get(String id) {
    if(store.get(id) == null){
      log.info("Customer is missing for ID "+id);
      throw new IllegalArgumentException("CUS-MISSING");
    }
    else{
      return store.get(id);
    }
  }
}
