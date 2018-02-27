package ru.otus.rik.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Statistics",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"prevIdStat"})}
      )
@Getter
@Setter
public class StatisticsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStat;

    private String markerName;
    private String pageName;
    private String clientIP;
    private String clientName;
    private String clientTime;
    private String serverTime;
    private Integer prevIdStat;
    private String origin;
}
