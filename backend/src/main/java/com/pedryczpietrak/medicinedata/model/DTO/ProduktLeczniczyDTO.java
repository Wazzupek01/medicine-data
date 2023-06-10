package com.pedryczpietrak.medicinedata.model.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProduktLeczniczyDTO(
        Integer id,
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
        List<SubstancjaCzynnaDTO> substancjeCzynne,
        boolean refundowany
) {
}
