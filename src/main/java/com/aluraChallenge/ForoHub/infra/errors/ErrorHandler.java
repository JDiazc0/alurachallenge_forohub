package com.aluraChallenge.ForoHub.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    public record ErrorResponse(String error, String message){}

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity Error404Handler(EntityNotFoundException e){
        var errorResponse = new ErrorResponse("Resource not found", e.getMessage());
        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity Error400Handler(MethodArgumentNotValidException e){
        var errors = e.getAllErrors();
        return ResponseEntity.badRequest().body(errors);
    }
}
