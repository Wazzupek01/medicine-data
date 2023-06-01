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
