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

@Entity
@Table(name = "opakowania")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Opakowania {
    @Id
    @XmlTransient
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @XmlElement(name = "opakowanie")
    @OneToMany(mappedBy = "opakowania", cascade = CascadeType.ALL)
    private List<Opakowanie> opakowania;

    @OneToOne(mappedBy = "opakowania")
    @XmlTransient
    private ProduktLeczniczy produktLeczniczy;
}
