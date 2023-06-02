package com.pedryczpietrak.medicinedata.model.entities.produkt_leczniczy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "substancja_czynna")
@XmlAccessorType(XmlAccessType.FIELD)
public class SubstancjaCzynna {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @XmlTransient
    private Integer id;

    @XmlValue
    private String substancjaCzynna;

    @XmlTransient
    @ManyToOne(optional = false)
    @JoinColumn(name = "substancje_czynne_id", referencedColumnName = "id")
    private SubstancjeCzynne substancjeCzynne;
}
