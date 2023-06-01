package com.pedryczpietrak.medicinedata.model.DTO;

import java.util.List;

public record produktLeczniczyDTO(
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
        List <SubstancjaCzynnaDTO> substancjeCzynne
){}
