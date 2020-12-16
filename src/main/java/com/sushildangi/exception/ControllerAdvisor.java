package com.sushildangi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<?> unauthorizedAccess() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Access Denied");

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }
}
