package ru.otus.rik.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@Table(name = "Role",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private int idRole;

    @XmlElement
    private String name;
}
