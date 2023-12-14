package com.example.finalprojectbootcamp.exceptions;

public class NegativeRatingException extends IllegalArgumentException{
    public NegativeRatingException() {
        super("The expert has a negative rating, so they do not have access to the system.");
    }
}
