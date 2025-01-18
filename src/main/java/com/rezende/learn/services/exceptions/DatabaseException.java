package com.rezende.learn.services.exceptions;

public class DatabaseException extends RuntimeException{

    public DatabaseException(String msg) {
        super(msg);
    }

    public DatabaseException(String msg, Object...args) {
        super(String.format(msg, args));
    }
}
