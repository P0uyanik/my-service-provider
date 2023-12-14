package com.example.finalprojectbootcamp.exceptions;

public class RateAndReviewException extends IllegalArgumentException {
    public RateAndReviewException() {
        super("The entered information does not match.");
    }
}