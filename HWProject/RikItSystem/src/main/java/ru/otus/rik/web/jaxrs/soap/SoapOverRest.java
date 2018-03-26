package ru.otus.rik.web.jaxrs.soap;

import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import ru.otus.rik.web.jaxrs.credit.CreditCalculator;
import ru.otus.rik.web.jaxws.geoipservice.GeoIpServiceAdapter;
import ru.otus.rik.web.jaxws.sunsetrise.RiseSetDateHolder;
import ru.otus.rik.web.jaxws.sunsetrise.SunRiseSetServiceAdapter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(tags={"soap"})
@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class SoapOverRest {
    private static final SunRiseSetServiceAdapter sunRiseSetServiceAdapter = new SunRiseSetServiceAdapter();
    private static final GeoIpServiceAdapter geoIpServiceAdapter = new GeoIpServiceAdapter();

    @GET
    @Path("/sunriseset")
    @ApiOperation("Get today's time of sunrise and sunset")
    @ApiResponses(
            @ApiResponse(response = SunRiseSetResponse.class, code = 200, message = "Returns time")
    )
    public Response getSunRiseSet(
            @ApiParam(value = "Latitude of the place", required = true)
            @Valid @NotNull @QueryParam("latitude") float latitude,

            @ApiParam(value = "Longitude of the place", required = true)
            @Valid @NotNull @QueryParam("longitude") float longitude) {

        RiseSetDateHolder dateHolder = sunRiseSetServiceAdapter.getTodaySunRiseSet(latitude, longitude);
        return Response.status(Response.Status.OK).entity(new SunRiseSetResponse(dateHolder)).build();
    }

    @Getter
    @Setter
    public static final class SunRiseSetResponse {
        private String rise;
        private String set;

        public SunRiseSetResponse(RiseSetDateHolder dateHolder) {
            rise = dateHolder.riseAsString();
            set = dateHolder.setAsString();
        }
    }

    @GET
    @Path("/iplocation")
    @ApiOperation("Get location of IP")
    @ApiResponses(
            @ApiResponse(response = IpLocationResponse.class, code = 200, message = "Returns location")
    )
    public Response getIpLocation(
            @ApiParam(value = "IP v4 address", required = true)
            @Valid @NotBlank @QueryParam("ip") String ip) {
        String location = geoIpServiceAdapter.getCountryByIP(ip);
        return Response.status(Response.Status.OK).entity(new IpLocationResponse(location)).build();
    }

    @AllArgsConstructor
    @Getter
    public static final class IpLocationResponse {
        String location;
    }
}
