package ru.otus.rik.web.jaxrs.credit;

import javax.ws.rs.QueryParam;

public class CreditParam {
    @QueryParam("total")
    int total;

    @QueryParam("period")
    int period;

    @QueryParam("percent")
    double percent;
}
