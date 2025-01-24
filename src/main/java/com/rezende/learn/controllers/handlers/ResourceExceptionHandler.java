package com.rezende.learn.controllers.handlers;

import com.rezende.learn.dtos.exceptions.CustomErrorDTO;
import com.rezende.learn.dtos.exceptions.ValidationErrorDTO;
import com.rezende.learn.services.exceptions.DatabaseException;
import com.rezende.learn.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorDTO> handleEntityErrorException(ResourceNotFoundException e, HttpServletRequest req) {
        Integer status = HttpStatus.BAD_REQUEST.value();
        CustomErrorDTO error = new CustomErrorDTO(Instant.now(), status, e.getMessage(), req.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomErrorDTO> handleDatabaseErrorException(DatabaseException e, HttpServletRequest req) {
        Integer status = HttpStatus.CONFLICT.value();
        CustomErrorDTO error = new CustomErrorDTO(Instant.now(), status, e.getMessage(), req.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationErrorDTO> handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest req) {
        Integer status = HttpStatus.UNPROCESSABLE_ENTITY.value();
        ValidationErrorDTO error = new ValidationErrorDTO(Instant.now(), status, "Validation Error", req.getRequestURI());
        e.getConstraintViolations().forEach(err -> error.addError(err.getPropertyPath().toString(), err.getMessage()));
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest req) {
        Integer status = HttpStatus.UNPROCESSABLE_ENTITY.value();
        ValidationErrorDTO error = new ValidationErrorDTO(Instant.now(), status, "Validation Error", req.getRequestURI());
        e.getBindingResult().getFieldErrors().forEach(err -> error.addError(err.getField(), err.getDefaultMessage()));
        return ResponseEntity.status(status).body(error);
    }
}
