package com.pedryczpietrak.medicinedata.model;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "substancjeCzynne", namespace = "http://rejestrymedyczne.ezdrowie.gov.pl/rpl/eksport-danych-v1.0")
public class SubstancjeCzynne {
    @XmlElement(name = "substancjaCzynna")
    private List<String> substancjeCzynne;
}
