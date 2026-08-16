package com.northstar.crm.api;

import com.northstar.crm.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.HttpStatus;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class GlobalExceptionHandler {
  private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, WebRequest request) {
    List<ErrorResponse.FieldViolation> violations = ex.getBindingResult().getFieldErrors().stream()
            .map(fe -> new ErrorResponse.FieldViolation(fe.getField(), fe.getDefaultMessage()))
            .toList();

    String correlationId = request.getHeader("X-Correlation-Id");

    ErrorResponse body = new ErrorResponse();
    body.setStatus(HttpStatus.BAD_REQUEST.value());
    body.setError("Bad Request");
    body.setMessage("Validation failed");
    body.setCorrelationId(correlationId);
    body.setViolations(violations);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleNotFound(IllegalArgumentException ex, WebRequest request) {
    String correlationId = request.getHeader("X-Correlation-Id");

    ErrorResponse body = new ErrorResponse();
    body.setStatus(HttpStatus.NOT_FOUND.value());
    body.setError("Not Found");
    body.setMessage(ex.getMessage());
    body.setCorrelationId(correlationId);
    body.setViolations(List.of());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
  }

  @ExceptionHandler(IllegalStateException.class)
  public ResponseEntity<ErrorResponse> handleConflict(IllegalStateException ex, WebRequest request) {
    String correlationId = request.getHeader("X-Correlation-Id");

    ErrorResponse body = new ErrorResponse();
    body.setStatus(HttpStatus.CONFLICT.value());
    body.setError("Conflict");
    body.setMessage(ex.getMessage());
    body.setCorrelationId(correlationId);
    body.setViolations(List.of());

    return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleSafe500(Exception ex, WebRequest request) {
    log.error("Unhandled exception", ex);

    String correlationId = request.getHeader("X-Correlation-Id");

    ErrorResponse body = new ErrorResponse();
    body.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    body.setError("Internal Server Error");
    body.setMessage("An unexpected error occurred");
    body.setCorrelationId(correlationId);
    body.setViolations(List.of());

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
  }
}