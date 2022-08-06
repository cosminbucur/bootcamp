package com.sda.spring.boot.rest.server.aop;

import com.sda.spring.boot.rest.server.exception.DuplicatePaperException;
import com.sda.spring.boot.rest.server.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException exception) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("Timestamp: ", LocalDateTime.now());
        body.put("Message:", exception.getLocalizedMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicatePaperException.class)
    public ResponseEntity<Object> handleDuplicatePaperException(DuplicatePaperException exception) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("Timestamp: ", LocalDateTime.now());
        body.put("Message:", exception.getLocalizedMessage());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleInternalServerError(RuntimeException exception) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("Timestamp: ", LocalDateTime.now());
        body.put("Message:", exception.getLocalizedMessage());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
