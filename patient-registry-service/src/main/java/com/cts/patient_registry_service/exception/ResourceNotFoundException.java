package com.cts.patient_registry_service.exception;
public class ResourceNotFoundException
        extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}