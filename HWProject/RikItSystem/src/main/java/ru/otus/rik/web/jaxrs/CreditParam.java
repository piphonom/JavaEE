package ru.otus.rik.web.jaxrs;

import javax.ws.rs.QueryParam;

public class CreditParam {
    @QueryParam("total")
    int total;

    @QueryParam("period")
    int period;

    @QueryParam("percent")
    double percent;
}
