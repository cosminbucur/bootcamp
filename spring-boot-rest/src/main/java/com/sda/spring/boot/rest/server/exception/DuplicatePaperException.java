package com.sda.spring.boot.rest.server.exception;

public class DuplicatePaperException extends RuntimeException {

    public DuplicatePaperException(String message) {
        super(message);
    }

}
