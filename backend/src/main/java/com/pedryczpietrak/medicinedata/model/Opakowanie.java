package com.pedryczpietrak.medicinedata.model;

import jakarta.persistence.*;
import lombok.*;

import javax.xml.bind.annotation.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "opakowanie")
@XmlType(propOrder = {"wielkosc", "jednostkaWielkosci", "kodEAN", "kategoriaDostepnosci", "skasowane", "numerEU",
        "dystrybutorRownolegly", "id"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Opakowanie {
    @XmlAttribute(name = "id")
    @Id
    private Integer id;

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

    @XmlTransient
    @ManyToOne(optional = false)
    @JoinColumn(name = "opakowania_id", referencedColumnName = "id")
    private Opakowania opakowania;
}
