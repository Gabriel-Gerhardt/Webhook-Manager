package com.manager.webhook.exception;


public class EventNotFoundException extends RuntimeException {
    String message;
    public EventNotFoundException(String message){
        super();
        this.message = message;
    }
}
