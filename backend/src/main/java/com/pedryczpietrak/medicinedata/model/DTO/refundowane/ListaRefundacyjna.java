package com.pedryczpietrak.medicinedata.model.DTO.refundowane;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ListaRefundacyjna")
public class ListaRefundacyjna {
    @XmlElement(name = "OpakowanieLeku")
    private List<OpakowanieLeku> opakowania;
}
