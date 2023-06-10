package com.pedryczpietrak.medicinedata.model.entities.produkt_leczniczy;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produkt_leczniczy")
@XmlType(propOrder = {"nazwaProduktu", "rodzajPreparatu", "nazwaPowszechnieStosowana", "moc", "postac",
        "podmiotOdpowiedzialny", "typProcedury", "numerPozwolenia", "waznoscPozwolenia", "kodATC", "id", "opakowania",
        "substancjeCzynne"})
@XmlAccessorType(XmlAccessType.FIELD)
public class ProduktLeczniczy implements Persistable<Integer> {
    @Id
    @XmlAttribute(name = "id")
    @Column(name = "id", length = 2048)
    private Integer id;

    @XmlAttribute(name = "nazwaProduktu")
    @Column(name = "nazwaProduktu", length = 2048)
    private String nazwaProduktu;

    @XmlAttribute(name = "rodzajPreparatu")
    @Column(name = "rodzajPreparatu", length = 2048)
    private String rodzajPreparatu;

    @XmlAttribute(name = "nazwaPowszechnieStosowana")
    @Column(name = "nazwaPowszechnieStosowana", length = 2048)
    private String nazwaPowszechnieStosowana;

    @XmlAttribute(name = "moc")
    @Column(name = "moc", length = 2048)
    private String moc;

    @XmlAttribute(name = "postac")
    private String postac;

    @XmlAttribute(name = "podmiotOdpowiedzialny")
    private String podmiotOdpowiedzialny;

    @XmlAttribute(name = "typProcedury")
    private String typProcedury;

    @XmlAttribute(name = "numerPozwolenia")
    private String numerPozwolenia;

    @XmlAttribute(name = "waznoscPozwolenia")
    private String waznoscPozwolenia;

    @XmlAttribute(name = "kodATC")
    private String kodATC;

    @XmlElement(name = "opakowania")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "opakowania_id", referencedColumnName = "id")
    private Opakowania opakowania;

    @XmlElement(name = "substancjeCzynne")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "substancje_czynne_id", referencedColumnName = "id")
    private SubstancjeCzynne substancjeCzynne;

    @XmlAttribute(name = "refundowany")
    private boolean refundowany;

    @Override
    public boolean isNew() {
        return true;
    }
}
