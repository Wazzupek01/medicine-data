package com.pedryczpietrak.medicinedata.exceptions;

public class EmailExistsException extends RuntimeException{
    public EmailExistsException(String message) {
        super("Email has to be unique!");
    }
}
