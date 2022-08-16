package com.example.demo.exception;

public class StudentEmptyNameException extends RuntimeException{
    public StudentEmptyNameException(String message) {
        super(message);
    }
}
