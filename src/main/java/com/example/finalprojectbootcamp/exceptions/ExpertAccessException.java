package com.example.finalprojectbootcamp.exceptions;

public class ExpertAccessException extends IllegalArgumentException {
    public ExpertAccessException() {
        super("The desired expert does not have access to the system and needs to be approved by the administrator.");
    }
}
