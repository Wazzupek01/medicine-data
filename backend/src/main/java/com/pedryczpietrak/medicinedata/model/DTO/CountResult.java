package com.pedryczpietrak.medicinedata.model.DTO;

public record CountResult(String name, Long value, Long value2) {

    public CountResult(String name, Long value, Long value2) {
        this.name = name;
        this.value = value;
        this.value2 = value2;
    }
}