package com.rezende.learn.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String msg) {
        super(msg);
    }

    public ResourceNotFoundException(String msg, Object...args) {
        super(String.format(msg, args));
    }
}
