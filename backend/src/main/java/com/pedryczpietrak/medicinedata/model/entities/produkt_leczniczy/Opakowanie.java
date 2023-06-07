package com.pedryczpietrak.medicinedata.model.entities.produkt_leczniczy;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "opakowanie")
@XmlType(propOrder = {"wielkosc", "jednostkaWielkosci", "kodEAN", "kategoriaDostepnosci", "skasowane", "numerEU",
        "dystrybutorRownolegly", "id"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Opakowanie implements Persistable<Integer> {
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

    @Override
    public boolean isNew() {
        return true;
    }
}
