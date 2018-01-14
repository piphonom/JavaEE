package ru.otus.rik.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table (name = "Position",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
@Getter
@Setter
public class PositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPosition;

    private String title;
    private int salary;
}
