package com.pedryczpietrak.medicinedata.model.entities.produkt_leczniczy;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "substancje_czynne")
@XmlAccessorType(XmlAccessType.FIELD)
public class SubstancjeCzynne {
    @Id
    @XmlTransient
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @XmlElement(name = "substancjaCzynna")
    @OneToMany(mappedBy = "substancjeCzynne", cascade = CascadeType.ALL)
    private List<SubstancjaCzynna> substancjeCzynne;

    @XmlTransient
    @OneToOne(mappedBy = "substancjeCzynne")
    private ProduktLeczniczy produktLeczniczy;
}
