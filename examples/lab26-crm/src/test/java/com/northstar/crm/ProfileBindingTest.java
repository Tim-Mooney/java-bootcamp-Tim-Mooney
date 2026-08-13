package com.northstar.crm;

import com.northstar.crm.config.NorthstarIntegrationProperties;
import com.northstar.crm.model.Customer;
import com.northstar.crm.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class ProfileBindingTest {

    @Autowired
    private NorthstarIntegrationProperties integrationProperties;

    @Autowired
    private CustomerService customerService;

    @Test
    void testProfileBindsPropertiesAndSeedsCustomers() {
        // application-test.yml overrides connect-timeout-ms to 100 (base default is 2000)
        assertEquals(100, integrationProperties.getConnectTimeoutMs());

        // api-base-url is not overridden in application-test.yml, so it falls through to application.yml
        assertEquals("http://localhost:9090", integrationProperties.getApiBaseUrl());

        // CustomerService seeds CUS-1001 regardless of active profile
        Customer amina = customerService.get("CUS-1001");
        assertEquals("Amina Khan", amina.getName());
    }
}