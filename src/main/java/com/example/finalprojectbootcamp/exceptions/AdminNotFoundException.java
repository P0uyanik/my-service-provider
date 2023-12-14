package com.example.finalprojectbootcamp.exceptions;

public class AdminNotFoundException extends IllegalArgumentException {
    public AdminNotFoundException() {
        super("The specified admin was not found. Please check the input information.");
    }
}
