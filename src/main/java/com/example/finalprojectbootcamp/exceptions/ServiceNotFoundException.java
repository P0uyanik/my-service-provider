package com.example.finalprojectbootcamp.exceptions;

public class ServiceNotFoundException extends IllegalArgumentException {
    public ServiceNotFoundException() {
        super("The desired service is not found or available. Please enter another service to add the subservice.");
    }
}
