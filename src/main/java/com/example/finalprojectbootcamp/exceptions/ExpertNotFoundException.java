package com.example.finalprojectbootcamp.exceptions;

public class ExpertNotFoundException extends IllegalArgumentException {
    public ExpertNotFoundException() {
        super("The desired expert is not found or available.");
    }
}
