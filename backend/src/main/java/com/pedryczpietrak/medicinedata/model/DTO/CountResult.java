package com.pedryczpietrak.medicinedata.model.DTO;

public record CountResult(String name, Long value) {

    public CountResult(String name, Long value) {
        this.name = name;
        this.value = value;
    }
}