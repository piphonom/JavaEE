package ru.otus.rik.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Role",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@Getter
@Setter
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRole;

    private String name;
}
