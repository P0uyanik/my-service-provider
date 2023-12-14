package com.example.finalprojectbootcamp.exceptions;

public class PageSizeException  extends IllegalArgumentException {
    public PageSizeException() {
        super("The desired PageSize  is not correct .");
    }
}