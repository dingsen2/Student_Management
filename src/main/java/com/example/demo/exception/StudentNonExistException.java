package com.example.demo.exception;

public class StudentNonExistException extends RuntimeException{
    public StudentNonExistException(String message) {
        super(message);
    }
}
