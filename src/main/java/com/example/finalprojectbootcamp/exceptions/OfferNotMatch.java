package com.example.finalprojectbootcamp.exceptions;

public class OfferNotMatch extends IllegalArgumentException{
    public OfferNotMatch() {
        super("The existing proposals do not match the requested proposal.");
    }
}
