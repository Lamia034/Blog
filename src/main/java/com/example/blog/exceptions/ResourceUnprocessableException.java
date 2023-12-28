package com.example.blog.exceptions;

public class ResourceUnprocessableException extends RuntimeException {
    public ResourceUnprocessableException(String message) {
        super(message);
    }

}
