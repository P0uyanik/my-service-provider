package com.example.finalprojectbootcamp.exceptions;

public class OfferNotFoundException extends IllegalArgumentException{
    public OfferNotFoundException() {
        super("The requested offer was not found with the given information.");
    }
}
