package com.project.library.exception;

import java.sql.SQLException;

public class MySQLException extends SQLException {
    private String message;
    public MySQLException(String message){
        this.message = message;
    }
}
