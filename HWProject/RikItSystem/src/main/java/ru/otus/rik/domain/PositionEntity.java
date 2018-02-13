package ru.otus.rik.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@Table (name = "Position",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private int idPosition;
    @XmlElement
    private String title;
    @XmlElement
    private int salary;

    @Override
    public int hashCode() {
        return idPosition ^ title.hashCode() ^ salary;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;

        return obj instanceof PositionEntity &&
                this.idPosition == ((PositionEntity) obj).idPosition &&
                this.title.equals(((PositionEntity) obj).title) &&
                this.salary == ((PositionEntity) obj).salary;
    }
}
