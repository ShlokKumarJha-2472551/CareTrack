package com.cts.patient_registry_service.exception;
public class DuplicateResourceException
        extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}