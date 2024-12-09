package com.aluraChallenge.ForoHub.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler {
    public record ErrorResponse(String error, String message){}
    public record ValidationError(String field, String message){}
    public record ValidationErrorResponse(String error, List<ValidationError> errors){};

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> Error404Handler(EntityNotFoundException e){
        var errorResponse = new ErrorResponse("Resource not found", e.getMessage());
        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> Error400Handler(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors()
                .stream()
                .map(fieldError -> new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();
        return ResponseEntity.badRequest().body(new ValidationErrorResponse("Validation failed", errors));
    }
}
