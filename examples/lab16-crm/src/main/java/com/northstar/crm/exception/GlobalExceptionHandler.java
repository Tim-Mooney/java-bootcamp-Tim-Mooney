package com.northstar.crm.exception;

import jakarta.validation.ConstraintViolation;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.time.Instant;
import java.util.logging.Logger;
import java.util.logging.Level;

public class GlobalExceptionHandler {
    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());


    public ErrorResponse fromBusiness(BusinessException ex) {
        return new ErrorResponse(
                Instant.now(),
                ex.getStatusHint(),
                ex.getCode(),
                ex.getMessage(),
                ex.getCorrelationId(),
                Map.of());
    }

    public ErrorResponse fromValidation(
            Set<? extends ConstraintViolation<?>> violations, String correlationId) {
        Map<String, String> fields = new LinkedHashMap<>();
        for (ConstraintViolation<?> v : violations) {
            fields.put(v.getPropertyPath().toString(), v.getMessage());
        }
        return new ErrorResponse(
                Instant.now(),400, "VALIDATION_FAILED", "Validation failed", correlationId, fields);
    }

    public ErrorResponse fromUnexpected(Exception ex, String correlationId) {
        logger.log(Level.SEVERE, "Unexpected error occurred: " + ex.getMessage(), ex);
        // Log full stack internally; do not put stack or ex.getMessage() if it may leak

        return new ErrorResponse(
                Instant.now(), 500, "INTERNAL_ERROR", "Unexpected server error", correlationId, Map.of());
    }
}