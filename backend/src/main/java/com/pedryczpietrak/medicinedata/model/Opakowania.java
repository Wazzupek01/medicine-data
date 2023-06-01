package com.pedryczpietrak.medicinedata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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
@ToString
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
