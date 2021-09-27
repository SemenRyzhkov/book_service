package com.github.template.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    @ControllerAdvice
    @Slf4j
    public class GlobalExceptionHandler {

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<ExeptionResponse> handleIllegalArgumentException(IllegalArgumentException e) {
            ExeptionResponse exeptionResponse = new ExeptionResponse(e.getMessage());
            log.warn(e.getMessage());
            return new ResponseEntity<>(exeptionResponse, HttpStatus.METHOD_NOT_ALLOWED); //TODO нежнее)
        }

        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity<ExeptionResponse> handleEntityNotFoundException(EntityNotFoundException e) {
            ExeptionResponse exeptionResponse = new ExeptionResponse(e.getMessage());
            log.warn(e.getMessage());
            return new ResponseEntity<>(exeptionResponse, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ExeptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
            ExeptionResponse exeptionResponse = new ExeptionResponse(e.getMessage());
            log.warn(e.getMessage());
            return new ResponseEntity<>(exeptionResponse, HttpStatus.BAD_REQUEST);
        }

    }
