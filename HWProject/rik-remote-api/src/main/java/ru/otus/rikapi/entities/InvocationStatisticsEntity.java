package ru.otus.rikapi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "InvocationStatistics")
@Getter
@Setter
public class InvocationStatisticsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idStat;

    private String methodName;
    private long invocationTime;
}
