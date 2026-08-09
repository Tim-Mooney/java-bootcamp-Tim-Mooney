package com.northstar.crm.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;



@Component
public class CustomerMetrics {
    private final MeterRegistry registry;
    private final Counter createSuccess;
    private final Counter createFailure;
    private final Counter getSuccess;
    private final Timer createTimer;
    private final Timer getTimer;


    public CustomerMetrics(MeterRegistry registry) {
        this.registry = registry;
        createSuccess = registry.counter("crm.customer.create", "result", "success");
        createFailure = registry.counter("crm.customer.create", "result", "failure");
        getSuccess = registry.counter("crm.customer.get", "result", "success");
        createTimer = registry.timer("crm.customer.create.latency");
        getTimer = registry.timer("crm.customer.get.latency");
    }

    public void recordCreate(String result) {
        Counter.builder("crm.customer.create").tag("result", result).register(registry).increment();
    }

    public void recordGet(String result) {
        Counter.builder("crm.customer.get").tag("result", result).register(registry).increment();
    }

    public double failCounter(){
        return createFailure.count();
    }
}
