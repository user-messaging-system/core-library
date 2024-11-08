package com.user_messaging_system.core_library.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.user_messaging_system.core_library.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.dao.DataIntegrityViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException{
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request){
        ErrorResponse errorResponse = 
        new ErrorResponse(ex.getMessage(), ex.getCause().getMessage(), HttpStatus.BAD_REQUEST.value(), request.getDescription(false));
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
        ErrorResponse errorResponse = 
        new ErrorResponse(ex.getMessage(), ex.getCause().getMessage(), HttpStatus.NOT_FOUND.value(), request.getDescription(false));
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({DataIntegrityViolationException.class, DublicateResourceException.class})
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
        ErrorResponse errorResponse = 
        new ErrorResponse(ex.getMessage(), ex.getCause().getMessage(), HttpStatus.CONFLICT.value(), request.getDescription(false));
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest request){
        ErrorResponse errorResponse = 
        new ErrorResponse(ex.getMessage(), ex.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), request.getDescription(false));
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
