package ru.otus.rik.domain.currency;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CbrValute {
    @XmlAttribute(name = "ID", required = true)
    private String id;

    @XmlElement(name = "NumCode", required = true)
    private int numCode;

    @XmlElement(name = "CharCode", required = true)
    private String charCode;

    @XmlElement(name = "Nominal", required = true)
    private int nominal;

    @XmlElement(name = "Name", required = true)
    private String name;

    @XmlElement(name = "Value", required = true)
    private String value;
}
