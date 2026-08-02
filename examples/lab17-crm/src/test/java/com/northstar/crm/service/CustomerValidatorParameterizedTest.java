package com.northstar.crm.service;

import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.repository.InMemoryCustomerRepository;
import com.northstar.crm.exception.BusinessException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class CustomerValidatorParameterizedTest {
    CustomerValidator validator = new CustomerValidator(new InMemoryCustomerRepository());

    @ParameterizedTest
    @CsvSource({
            "PROSPECT,ACTIVE",
            "PROSPECT,CLOSED",
            "ACTIVE,SUSPENDED",
            "ACTIVE,CLOSED",
            "SUSPENDED,ACTIVE"
    })
    void legalTransitions(CustomerStatus from, CustomerStatus to) {
        assertDoesNotThrow(() ->
                validator.validateTransition(from, to, "lab-request-001"));
    }

    @ParameterizedTest
    @CsvSource({
            // TODO: illegal rows e.g. ACTIVE,PROSPECT and CLOSED,ACTIVE
            "ACTIVE,PROSPECT",
            "ACTIVE,ACTIVE",
            "PROSPECT,PROSPECT",
            "SUSPENDED,SUSPENDED",
            "CLOSED,ACTIVE",
            "CLOSED,PROSPECT",
            "CLOSED,CLOSED"
    })
    void illegalTransitions(CustomerStatus from, CustomerStatus to) {
        BusinessException ex = assertThrows(BusinessException.class,
                () -> validator.validateTransition(from, to, "lab-request-002"));

        assertEquals("BUSINESS_CONFLICT", ex.getCode());
        assertEquals(409, ex.getStatusHint());
    }
}
