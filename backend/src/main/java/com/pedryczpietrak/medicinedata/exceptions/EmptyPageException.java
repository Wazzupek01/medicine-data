package com.pedryczpietrak.medicinedata.exceptions;

public class EmptyPageException extends RuntimeException {
    public EmptyPageException() {
        super("Page does not exist!");
    }
}
