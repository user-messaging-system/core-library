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
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception,
            WebRequest request
    ){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse.Builder()
                .message(exception.getMessage())
                .error(exception.getCause().getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getDescription(false))
                .build()
            );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException exception,
            WebRequest request
    ){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse.Builder()
                .message(exception.getMessage())
                .error(exception.getCause().getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getDescription(false))
                .build()
            );
    }

    @ExceptionHandler({DataIntegrityViolationException.class, DublicateResourceException.class})
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(
            DataIntegrityViolationException exception,
            WebRequest request
    ){
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(new ErrorResponse.Builder()
                .message(exception.getMessage())
                .error(exception.getCause().getMessage())
                .status(HttpStatus.CONFLICT.value())
                .path(request.getDescription(false))
                .build()
            );
    }
}
