package com.pedryczpietrak.medicinedata.model;

import jakarta.persistence.*;
import lombok.*;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
