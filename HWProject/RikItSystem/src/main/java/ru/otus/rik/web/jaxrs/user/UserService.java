package ru.otus.rik.web.jaxrs.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.otus.rik.domain.UserEntity;
import ru.otus.rik.service.helpers.PersistenceServiceHolder;
import ru.otus.rik.service.persistence.PersistenceService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/user")
public class UserService {

    private final static PersistenceService persistenceService = PersistenceServiceHolder.getPersistenceService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getUsersList() {
        List<UserEntity> usersList = persistenceService.findAllUsers();
        UsersListResponse response = new UsersListResponse(usersList);
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @AllArgsConstructor
    @Getter
    public final class UsersListResponse {
        private final List<UserEntity> users;
    }
}
