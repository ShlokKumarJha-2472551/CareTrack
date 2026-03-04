package com.cts.patient_registry_service.exception;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

import java.util.HashMap;

import java.util.Map;

@RestControllerAdvice

@Slf4j

public class GlobalExceptionHandler {

    public record ErrorResponse(

            LocalDateTime timestamp,

            int status,

            String error,

            String message,

            String path) {}

    @ExceptionHandler(ResourceNotFoundException.class)

    public ResponseEntity<ErrorResponse> handleNotFound(

            ResourceNotFoundException ex,

            WebRequest request) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)

                .body(new ErrorResponse(

                        LocalDateTime.now(), 404,

                        "Not Found", ex.getMessage(),

                        request.getDescription(false)

                                .replace("uri=", "")));

    }

    @ExceptionHandler(DuplicateResourceException.class)

    public ResponseEntity<ErrorResponse> handleDuplicate(

            DuplicateResourceException ex,

            WebRequest request) {

        return ResponseEntity.status(HttpStatus.CONFLICT)

                .body(new ErrorResponse(

                        LocalDateTime.now(), 409,

                        "Conflict", ex.getMessage(),

                        request.getDescription(false)

                                .replace("uri=", "")));

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)

    public ResponseEntity<Map<String, Object>> handleValidation(

            MethodArgumentNotValidException ex) {

        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {

            String fieldName = ((FieldError) error).getField();

            String errorMessage = error.getDefaultMessage();

            fieldErrors.put(fieldName, errorMessage);

        });

        Map<String, Object> body = new HashMap<>();

        body.put("timestamp", LocalDateTime.now());

        body.put("status", 400);

        body.put("error", "Validation Failed");

        body.put("fieldErrors", fieldErrors);

        return ResponseEntity.badRequest().body(body);

    }

    @ExceptionHandler(Exception.class)

    public ResponseEntity<ErrorResponse> handleGeneric(

            Exception ex, WebRequest request) {

        log.error("Unexpected error: ", ex);

        return ResponseEntity

                .status(HttpStatus.INTERNAL_SERVER_ERROR)

                .body(new ErrorResponse(

                        LocalDateTime.now(), 500,

                        "Internal Server Error",

                        "Something went wrong.",

                        request.getDescription(false)

                                .replace("uri=", "")));

    }

}
