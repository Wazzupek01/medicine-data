package com.pedryczpietrak.medicinedata.model;

import jakarta.persistence.*;
import lombok.*;

import javax.xml.bind.annotation.*;
import java.util.List;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class ProduktyLecznicze {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @XmlTransient
    private Integer id;

    @XmlAttribute(name = "stanNaDzien")
    private String stanNaDzien;

    @XmlElement(name = "produktLeczniczy")
    private List<ProduktLeczniczy> produktyLecznicze;

}
