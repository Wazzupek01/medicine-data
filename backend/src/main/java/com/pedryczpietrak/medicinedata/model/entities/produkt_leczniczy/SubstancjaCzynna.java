package com.pedryczpietrak.medicinedata.model.entities.produkt_leczniczy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

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
public class SubstancjaCzynna implements Persistable<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seqGen", sequenceName = "seq")
    @XmlTransient
    private Integer id;

    @XmlValue
    private String substancjaCzynna;

    @XmlTransient
    @ManyToOne(optional = false)
    @JoinColumn(name = "substancje_czynne_id", referencedColumnName = "id")
    private SubstancjeCzynne substancjeCzynne;

    @Override
    public boolean isNew() {
        return true;
    }
}
