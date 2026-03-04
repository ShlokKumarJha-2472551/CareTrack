package com.cts.caretrack.order_service.exception;

//import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice

public class OrderGlobalExceptionHandler {

    @ExceptionHandler(com.cts.caretrack.order_service.exception.ResourceNotFoundException.class)
    public ResponseEntity<?> notFound(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("time", LocalDateTime.now(),"error",e.getMessage()));
    }

    @ExceptionHandler(com.cts.caretrack.order_service.exception.BadRequestException.class)
    public  ResponseEntity<?> badRequest(BadRequestException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("time",LocalDateTime.now(),"error",e.getMessage()));
    }
}
