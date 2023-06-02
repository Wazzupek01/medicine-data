package com.pedryczpietrak.medicinedata.model.DTO;

import lombok.Builder;

import java.util.List;

@Builder

public record ProduktLeczniczyDTO(
        String nazwaProduktu,
        String rodzajPreparatu,
        String nazwaPowszechnieStosowana,
        String moc,
        String postac,
        String podmiotOdpowiedzialny,
        String typProcedury,
        String numerPozwolenia,
        String waznoscPozwolenia,
        String kodATC,
        List<OpakowanieDTO> opakowania,
        List<SubstancjaCzynnaDTO> substancjeCzynne
) {
}
