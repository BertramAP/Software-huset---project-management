package com.example.errors;

public class DuplicateActivitiesIDException extends RuntimeException {
    public DuplicateActivitiesIDException(String message) {
        super(message);
    }
}
