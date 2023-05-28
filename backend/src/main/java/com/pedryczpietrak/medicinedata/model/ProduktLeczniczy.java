package com.pedryczpietrak.medicinedata.model;

import lombok.*;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class ProduktLeczniczy {

    @XmlAttribute(name ="nazwaProduktu")
    private String nazwaProduktu;
    @XmlAttribute(name ="rodzajPreparatu")
    private String rodzajPreparatu;
    @XmlAttribute(name ="nazwaPowszechnieStosowana")
    private String nazwaPowszechnieStosowana;
    @XmlAttribute(name ="moc")
    private String moc;
    @XmlAttribute(name ="postac")
    private String postac;
    @XmlAttribute(name ="podmiotOdpowiedzialny")
    private String podmiotOdpowiedzialny;
    @XmlAttribute(name ="typProcedury")
    private String typProcedury;
    @XmlAttribute(name ="numerPozwolenia")
    private String numerPozwolenia;
    @XmlAttribute(name ="waznoscPozwolenia")
    private String waznoscPozwolenia;
    @XmlAttribute(name ="kodATC")
    private String kodATC;
    @XmlAttribute(name ="id")
    private int id;
    @XmlElement(name  = "opakowania")
    private Opakowania opakowania;
    @XmlElement(name = "substancjeCzynne")
    private SubstancjeCzynne substancjeCzynne;

}
