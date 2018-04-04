package ru.otus.rik.service.statistics;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/*
 * Due to statistics service is @Remote EJB service, its params should be serializable
  * */

@Builder
@Getter
public class StatisticsData implements Serializable {
    public static final long serialVersionUID = 123456789L;

    private String userTime;
    private String pageName;
    private String prevId;
    private String clientName;
    private String clientIP;
    private String origin;
}
