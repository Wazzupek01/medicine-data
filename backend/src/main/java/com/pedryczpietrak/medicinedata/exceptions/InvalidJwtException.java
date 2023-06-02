package com.pedryczpietrak.medicinedata.exceptions;

public class InvalidJwtException extends RuntimeException {
    public InvalidJwtException() {
        super("Invalid or inactive authorization token!");
    }
}
