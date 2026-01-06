package com.manager.webhook.exception;

public class WebhookException extends RuntimeException{
    String message;
    String code;
    public WebhookException(String message, String code){
        super();
        this.message = message;
        this.code = code;
    }

}
