package com.pedryczpietrak.medicinedata.exceptions;

public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException(String name) {
        super("Role " + name + " does not exist");
    }
}
