import {MdOpakowanieDto} from "./md-opakowanie-dto";
import {MdSubstancjaCzynnaDto} from "./md-substancja-czynna-dto";

export interface MdProduktLeczniczyDto {
    nazwaProduktu: string;
    rodzajPreparatu: string;
    nazwaPowszechnieStosowana: string;
    moc: string;
    postac: string;
    podmiotOdpowiedzialny: string;
    typProcedury: string;
    numerPozwolenia: string;
    waznoscPozwolenia: string;
    kodATC: string;
    opakowania: MdOpakowanieDto[];
    substancjeCzynne: MdSubstancjaCzynnaDto[];
}
