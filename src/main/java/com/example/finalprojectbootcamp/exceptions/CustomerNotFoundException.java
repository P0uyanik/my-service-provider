package com.example.finalprojectbootcamp.exceptions;

public class CustomerNotFoundException extends IllegalArgumentException{
    public CustomerNotFoundException() {
        super("The customer with the given information was not found, and they have not registered");
    }
}
