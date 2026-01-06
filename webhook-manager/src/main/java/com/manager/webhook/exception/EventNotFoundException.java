package com.manager.webhook.exception;

import java.sql.SQLException;

public class EventNotFoundException extends RuntimeException {
    String message;
    public EventNotFoundException(String message){
        super();
        this.message = message;
    }
}
