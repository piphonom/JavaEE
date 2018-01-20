package ru.otus.rik.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@Table(name = "Department",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "location"})})
@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlAttribute(name = "id")
    private int idDepartment;

    @XmlElement
    private String name;

    @XmlElement
    private String location;
}
