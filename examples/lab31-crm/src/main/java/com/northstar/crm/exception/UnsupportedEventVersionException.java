package com.northstar.crm.exception;

public class UnsupportedEventVersionException extends RuntimeException {
    public UnsupportedEventVersionException(String eventId) {
        super("Unsupported Event Version " + eventId);
    }
}
