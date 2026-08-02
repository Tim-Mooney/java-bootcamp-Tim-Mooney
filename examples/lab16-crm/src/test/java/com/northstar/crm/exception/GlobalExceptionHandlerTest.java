package com.northstar.crm.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.northstar.crm.dto.CustomerRequestDTO;
import com.northstar.crm.api.ApiResult;


class GlobalExceptionHandlerTest {
    GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void mapsNotFoundTo404() {
        ErrorResponse response = handler.fromBusiness(BusinessException.notFound("CUS-9999", "lab-request-001"));
        assertEquals(404, response.getStatus());
        assertEquals("CUSTOMER_NOT_FOUND", response.getError());

    }

    @Test
    void mapsConflictTo409() {
        var handler = new GlobalExceptionHandler();
        var response = handler.fromBusiness(BusinessException.conflict("illegal status transition ACTIVE -> PROSPECT", "lab-request-001"));
        assertEquals(409, response.getStatus());

    }

    @Test
    void unexpectedIsGeneric500() {
        ErrorResponse response = handler.fromUnexpected(new NullPointerException(), "lab-request-001");
        assertEquals(500, response.getStatus());
        assertEquals("INTERNAL_ERROR", response.getError());
    }
    @Test
    void mapsNotFound() {
        var handler = new GlobalExceptionHandler();
        var err = handler.fromBusiness(
                BusinessException.notFound("CUS-9999", "lab-request-001"));
        assertEquals(404, err.getStatus());
        assertEquals("lab-request-001", err.getCorrelationId());
    }

    @Test
    void mapsConflict() {
        var err = handler.fromBusiness(
                BusinessException.conflict("illegal status transition ACTIVE -> PROSPECT", "lab-request-001"));
        assertEquals(409, err.getStatus());
    }
}
