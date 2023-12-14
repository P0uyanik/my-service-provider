package com.example.finalprojectbootcamp.exceptions;

public class OrderNotFoundException extends IllegalArgumentException{
    public OrderNotFoundException() {
        super("The requested order was not found with the given information.");
    }
}
