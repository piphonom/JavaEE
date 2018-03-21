package ru.otus.rik.web.jaxrs.credit;

import io.swagger.annotations.*;
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

@Api(tags={"calculator"})
@Path("/credit/calculator")
@Produces(MediaType.APPLICATION_JSON)
public class CreditCalculator {
    @GET
    @Path("/annuity")
    @ApiOperation("Annuity credit calculation")
    @ApiResponses(
        @ApiResponse(response = AnnuityPayment.class, code = 200, message = "Returns monthly payment")
    )
    public Response annuityPayment(
            @ApiParam(value = "Credit amount", required = true)
            @QueryParam("total") int total,
            @ApiParam(value = "Period of credit as number of months", required = true)
            @QueryParam("period") int period,
            @ApiParam(value = "Percent of the credit", required = true)
            @QueryParam("percent") double percent) {

        percent /= 1200;
        double payment = (total * percent) / (1 - (1 / Math.pow(1 + percent, period)));
        AnnuityPayment annuityPayment = new AnnuityPayment(payment);
        return Response.status(Response.Status.OK).entity(annuityPayment).build();
    }

    @GET
    @Path("/differential")
    @ApiOperation("Differential credit calculation")
    @ApiResponses(
            @ApiResponse(response = DifferentialPayment.class, code = 200, message = "Returns schedule of monthly payments")
    )
    public Response differentialPayment(
            @ApiParam(value = "Credit amount", required = true)
            @QueryParam("total") int total,
            @ApiParam(value = "Period of credit as number of months", required = true)
            @QueryParam("period") int period,
            @ApiParam(value = "Percent of the credit", required = true)
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
