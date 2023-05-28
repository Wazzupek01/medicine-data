package com.pedryczpietrak.medicinedata.model;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class Opakowanie {
    @XmlAttribute(name = "wielkosc")
    private int wielkosc;
    @XmlAttribute(name = "jednostkaWielkosci")
    private String jednostkaWielkosci;
    @XmlAttribute(name = "kodEAN")
    private String kodEAN;
    @XmlAttribute(name = "kategoriaDostepnosci")
    private String kategoriaDostepnosci;
    @XmlAttribute(name = "skasowane")
    private String skasowane;
    @XmlAttribute(name = "numerEU")
    private String numerEU;
    @XmlAttribute(name = "dystrybutorRownolegly")
    private String dystrybutorRownolegly;
    @XmlAttribute(name="id")
    private int id;
}
