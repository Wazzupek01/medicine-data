package com.pedryczpietrak.medicinedata.model.DTO;

public record OpakowanieDTO(
        int wielkosc,
        String jednostkaWielkosci,
        String kodEAN,
        String kategoriaDostepnosci,
        String skasowane,
        String numerEU,
        String dystrybutorRownolegly
) {
}
