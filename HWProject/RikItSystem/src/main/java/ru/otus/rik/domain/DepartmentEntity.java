package ru.otus.rik.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Department",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "location"})})
@Getter
@Setter
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDepartment;

    private String name;
    private String location;
}
