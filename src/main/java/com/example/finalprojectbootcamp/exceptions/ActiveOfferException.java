package com.example.finalprojectbootcamp.exceptions;

public class ActiveOfferException extends IllegalArgumentException{
    ActiveOfferException(String s){
        super("The number of offers accepted for the order is either more than one or doesn't exist." + s);
    }
}
