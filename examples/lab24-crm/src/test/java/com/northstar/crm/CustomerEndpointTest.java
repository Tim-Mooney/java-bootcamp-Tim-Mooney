package com.northstar.crm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

@SpringBootTest
class CustomerEndpointTest {

    @Autowired
    private ApplicationContext applicationContext;

    private MockWebServiceClient mockClient;

    @BeforeEach
    void setUp() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
    void getCustomerReturnsCus1001() throws Exception {
        String request =
                "<GetCustomerRequest xmlns='http://northstar.com/crm/customers'>" +
                        "  <customerId>CUS-1001</customerId>" +
                        "</GetCustomerRequest>";

        String expectedResponse =
                "<GetCustomerResponse xmlns='http://northstar.com/crm/customers'>" +
                        "  <customerId>CUS-1001</customerId>" +
                        "  <name>Amina Khan</name>" +
                        "  <email>amina.khan@example.com</email>" +
                        "  <status>ACTIVE</status>" +
                        "</GetCustomerResponse>";

        mockClient.sendRequest(withPayload(new StringSource(request)))
                .andExpect(payload(new StringSource(expectedResponse)));
    }
}