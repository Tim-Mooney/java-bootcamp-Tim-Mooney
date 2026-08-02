package com.northstar.crm.exception;

import java.time.Instant;
import java.util.Map;

public class ErrorResponse {
    private final Instant timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String correlationId;
    private final Map<String, String> errors;

    public ErrorResponse(Instant timestamp, int status, String error, String message, String correlationId, Map<String, String> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.correlationId = correlationId;
        this.errors = errors;
    }

    //GETTERS


    public Instant getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public String toJson() {
        StringBuilder jsonString = new StringBuilder("{\"timestamp\":\"" + timestamp +
                "\",\"status\":" + status +
                ",\"error\":\"" + error +
                "\",\"message\":\"" + message +
                "\",\"correlationId\":\"" + correlationId +
                "\",\"errors\":{");
        boolean first = true;
        for (Map.Entry<String, String> entry : errors.entrySet()) {
            if (!first) {
                jsonString.append(",");
            }
            jsonString.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\"");
            first = false;
        }
        jsonString.append("}}");
        return jsonString.toString();
    }

}
