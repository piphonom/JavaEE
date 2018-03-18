package ru.otus.rik.web.jaxrs.credit;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/credit/calculator")
public class CreditCalculator {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/annuity")
    public Response annuityPayment(@QueryParam("total") int total,
                                  @QueryParam("period") int period,
                                  @QueryParam("percent") double percent) {

        percent /= 1200;
        double payment = (total * percent) / (1 - (1 / Math.pow(1 + percent, period)));
        AnnuityPayment annuityPayment = new AnnuityPayment(payment);
        return Response.status(Response.Status.OK).entity(annuityPayment).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/differential")
    public Response differentialPayment(@QueryParam("total") int total,
                                  @QueryParam("period") int period,
                                  @QueryParam("percent") double percent) {

        percent /= 1200;
        List<Double> payments = new ArrayList<>();
        double payed = 0;
        int i = 1;
        while (i <= period) {
            double payment = total/period + (total * (period - i++ + 1) * percent / period);
            payments.add(payment);
            payed += payment;
        }
        DifferentialPayment paymentsSchedule = new DifferentialPayment(payments);
        return Response.status(Response.Status.OK).entity(paymentsSchedule).build();
    }

    @AllArgsConstructor
    @Getter
    public final static class AnnuityPayment {
        private final double payment;
    }

    @AllArgsConstructor
    @Getter
    public final static class DifferentialPayment {
        private final List<Double> schedule;
    }
}
