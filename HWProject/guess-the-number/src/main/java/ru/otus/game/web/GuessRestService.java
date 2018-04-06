package ru.otus.game.web;

import org.hibernate.validator.constraints.NotBlank;
import ru.otus.game.service.GuessNumberService;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Path("/guess")
@Produces(MediaType.APPLICATION_JSON)
public class GuessRestService {
    @EJB
    private GuessNumberService numberService;

    @GET
    @Path("/number")
    public Response guessNumber(
                @Valid @NotBlank @QueryParam("name") String name,

                @Valid @Max(value = GuessNumberService.BOUND, message = "Number should be between 0 and 10")
                @QueryParam("number") int number) {

        Future<Boolean> future = numberService.guessTheNumber(name, number);
        try {
            boolean result = future.get();
            return Response.ok().entity(new ResultWrapper(result)).build();
        } catch (InterruptedException e) {
            return Response.serverError().build();
        } catch (ExecutionException e) {
            return Response.serverError().build();
        }
    }

    public static class ResultWrapper {
        private boolean guessed;

        public ResultWrapper(boolean guessed) {
            this.guessed = guessed;
        }

        public boolean getGuessed() {
            return guessed;
        }

        public void setGuessed(boolean guessed) {
            this.guessed = guessed;
        }
    }
}
