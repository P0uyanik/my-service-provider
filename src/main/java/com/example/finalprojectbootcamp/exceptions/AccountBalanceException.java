package com.example.finalprojectbootcamp.exceptions;

public class AccountBalanceException extends IllegalArgumentException{
    AccountBalanceException(String input){
        super("The customer's credit amount for payment is insufficient. The  required payment amount is"+ input );
    }
}
