package com.example.hw23;

public class EmployeeAlreadyAddedException extends Exception {
    public EmployeeAlreadyAddedException(String message) {
        super(message);
    }
}