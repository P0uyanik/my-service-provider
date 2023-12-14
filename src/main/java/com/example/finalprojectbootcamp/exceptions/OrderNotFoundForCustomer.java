package com.example.finalprojectbootcamp.exceptions;

public class OrderNotFoundForCustomer extends IllegalArgumentException{
    public OrderNotFoundForCustomer() {
        super("This order was not found for this customer.");
    }
}
