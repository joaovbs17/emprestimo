package com.emprestimo.empresax.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ApiExceptionHandler {

    public static final String GENERIC_ERROR_MESSAGE = "ERRO INTERNO";

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Object> handleConflitBusiness(BaseException ex, WebRequest request) {

        HttpStatus status = ex.getHttpStatus();

        ExceptionBase problem = ExceptionBase.builder()
                .status(status.value())
                .userMessage(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(problem, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaughtException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ex.printStackTrace();
        ExceptionBase problem = ExceptionBase.builder()
                .title(status.getReasonPhrase())
                .status(status.value())
                .userMessage(GENERIC_ERROR_MESSAGE)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(problem, status);
    }
  
}