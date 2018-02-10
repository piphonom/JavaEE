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
}
