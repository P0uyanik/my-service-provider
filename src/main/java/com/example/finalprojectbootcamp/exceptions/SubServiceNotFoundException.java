package com.example.finalprojectbootcamp.exceptions;

public class SubServiceNotFoundException extends IllegalArgumentException {
    public SubServiceNotFoundException() {
        super("The desired subservice is not found or available. Please enter another sub-service");
    }
}
