package com.project.library.utils.exception;

public class IdAlreadyExistsException extends RuntimeException{
    private String message;

    public IdAlreadyExistsException(String message){
        super();
        this.message = message;
    }

}
