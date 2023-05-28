package com.pedryczpietrak.medicinedata.model;

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

    @XmlAttribute(name = "stanNaDzien")
    private String stanNaDzien;

    @XmlElement(name = "produktLeczniczy")
    private List<ProduktLeczniczy> produktyLecznicze;

}
