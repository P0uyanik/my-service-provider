package com.example.finalprojectbootcamp.exceptions;

public class StartTimeException  extends IllegalArgumentException{
    public StartTimeException() {
        super("The work start time for the expert has not arrived yet, and for this reason, it is not possible to change the order status.");
    }
}
