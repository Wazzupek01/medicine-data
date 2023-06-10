package com.pedryczpietrak.medicinedata.model.mappers;

import com.pedryczpietrak.medicinedata.model.DTO.OpakowanieDTO;
import com.pedryczpietrak.medicinedata.model.DTO.ProduktLeczniczyDTO;
import com.pedryczpietrak.medicinedata.model.DTO.SubstancjaCzynnaDTO;
import com.pedryczpietrak.medicinedata.model.entities.produkt_leczniczy.Opakowanie;
import com.pedryczpietrak.medicinedata.model.entities.produkt_leczniczy.ProduktLeczniczy;
import com.pedryczpietrak.medicinedata.model.entities.produkt_leczniczy.SubstancjaCzynna;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProduktLeczniczyDTOMapper {
    @Named("produktLeczniczyToProduktLeczniczyDTO")
    public ProduktLeczniczyDTO produktLeczniczyToProduktLeczniczyDTO(ProduktLeczniczy p) {
        OpakowanieDTOMapper opakowanieMapper = Mappers.getMapper(OpakowanieDTOMapper.class);
        SubstancjaCzynnaDTOMapper substancjaCzynnaMapper = Mappers.getMapper(SubstancjaCzynnaDTOMapper.class);

        List<OpakowanieDTO> opakowanieDTO = null;
        List<SubstancjaCzynnaDTO> substancjaCzynnaDTO = null;
        if(p.getOpakowania() != null) {
            opakowanieDTO = new ArrayList<>();
            for (Opakowanie o : p.getOpakowania().getOpakowania()) {
                opakowanieDTO.add(opakowanieMapper.opakowanieToOpakowanieDTO(o));
            }
        }
        if(p.getSubstancjeCzynne() != null) {
            substancjaCzynnaDTO = new ArrayList<>();
            for (SubstancjaCzynna s : p.getSubstancjeCzynne().getSubstancjeCzynne()) {
                substancjaCzynnaDTO.add(substancjaCzynnaMapper.substancjaCzynnaToSubstancjaCzynnaDTO(s));
            }
        }

        return ProduktLeczniczyDTO.builder().id(p.getId())
                .nazwaProduktu(p.getNazwaProduktu())
                .rodzajPreparatu(p.getRodzajPreparatu())
                .nazwaPowszechnieStosowana(p.getNazwaPowszechnieStosowana())
                .moc(p.getMoc())
                .postac(p.getPostac())
                .podmiotOdpowiedzialny(p.getPodmiotOdpowiedzialny())
                .typProcedury(p.getTypProcedury())
                .numerPozwolenia(p.getNumerPozwolenia())
                .waznoscPozwolenia(p.getWaznoscPozwolenia())
                .kodATC(p.getKodATC())
                .opakowania(opakowanieDTO)
                .substancjeCzynne(substancjaCzynnaDTO).build();
    }
}
