package com.example.finalprojectbootcamp.exceptions;

public class SelectedOfferException extends IllegalArgumentException{
    public SelectedOfferException() {
        super("For this order, a proposal has already been selected.");
    }
}
