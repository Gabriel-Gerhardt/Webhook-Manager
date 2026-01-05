package com.manager.webhook.exception;

public class NoValidEventFound extends RuntimeException {
    private String message;
    public NoValidEventFound(String message){
        super();
        this.message = message;
    }
}
