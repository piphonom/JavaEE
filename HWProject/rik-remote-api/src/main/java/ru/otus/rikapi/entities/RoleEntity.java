package ru.otus.rikapi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Role",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RoleEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private int idRole;

    @XmlElement
    private String name;

//    @ManyToMany(mappedBy = "roles")
//    @XmlTransient
//    private Set<UserEntity> users = new HashSet<>();
}
