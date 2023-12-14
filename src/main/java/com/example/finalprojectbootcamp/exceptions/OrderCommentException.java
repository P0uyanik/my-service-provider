package com.example.finalprojectbootcamp.exceptions;

public class OrderCommentException extends IllegalArgumentException{
    public OrderCommentException() {
        super("Commenting is not possible for this order as it has not been completed yet.");
    }
}
