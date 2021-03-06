package ru.otus.rik.domain.currency;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "ValCurs")
@XmlSeeAlso(CbrValute.class)
public class CbrValuteList extends ArrayList<CbrValute> {
    public CbrValuteList() {
        super();
    }

    @XmlElement(name = "Valute")
    public List<CbrValute> getUserEntities() {
        return this;
    }
}
