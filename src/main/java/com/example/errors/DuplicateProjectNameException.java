package com.example.errors;

// Written by MJ
public class DuplicateProjectNameException extends RuntimeException {
    public DuplicateProjectNameException(String message) {
        super(message);
    }
}