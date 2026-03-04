package com.cts.authservice.exception;

import com.cts.authservice.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ApiErrorResponse> handleRuntime(RuntimeException ex){
//        return new ResponseEntity<>(
//                new ApiErrorResponse(
//                        HttpStatus.BAD_REQUEST.value(),
//                        ex.getMessage(),
//                        LocalDateTime.now()),
//                HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneric(Exception ex){
        return new ResponseEntity<>(
                new ApiErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Internal Server Error",
                        LocalDateTime.now()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleEmailExists(EmailAlreadyExistsException ex){
        return new ResponseEntity<>(
                new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(),
                        "Email Already Exists",
                        LocalDateTime.now()),
                HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidCredentials(InvalidCredentialsException ex){
        return new ResponseEntity<>(
                new ApiErrorResponse(HttpStatus.UNAUTHORIZED.value(),
                        ex.getMessage(),
                        LocalDateTime.now()),
                HttpStatus.UNAUTHORIZED
        );
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(ResourceNotFoundException ex){
        return new ResponseEntity<>(
                new ApiErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage(),
                        LocalDateTime.now()
                ),HttpStatus.NOT_FOUND
        );
    }
    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalid(InvalidRoleException ex){
        return new ResponseEntity<>(
                new ApiErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage(),
                        LocalDateTime.now()
                ),HttpStatus.BAD_REQUEST
        );
    }

}
