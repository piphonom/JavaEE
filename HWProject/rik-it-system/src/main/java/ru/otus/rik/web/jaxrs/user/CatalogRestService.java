package ru.otus.rik.web.jaxrs.user;

import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.otus.rikapi.entities.DepartmentEntity;
import ru.otus.rikapi.entities.PositionEntity;
import ru.otus.rikapi.entities.UserEntity;
import ru.otus.rikapi.service.UserService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(tags={"rik_catalog"})
@Path("/catalog")
@Produces(MediaType.APPLICATION_JSON)
public class CatalogRestService {

    //@EJB
    @Inject
    @Named("userService")
    private UserService userService;

    @GET
    @Path("/users")
    @ApiOperation("Get users list")
    @ApiResponses(
            @ApiResponse(response = UsersListResponse.class, code = 200, message = "Users list")
    )
    public Response getUsers() {
        List<UserEntity> users = userService.findAllUsers();
        UsersListResponse response = new UsersListResponse(users);
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @GET
    @Path("/departments")
    @ApiOperation("Get departments list")
    @ApiResponses(
            @ApiResponse(response = DepartmentsListResponse.class, code = 200, message = "Departments list")
    )
    public Response getDepartments() {
        List<DepartmentEntity> departments = userService.findAllDepartments();
        DepartmentsListResponse response = new DepartmentsListResponse(departments);
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @GET
    @Path("/positions")
    @ApiOperation("Get positions list")
    @ApiResponses(
            @ApiResponse(response = PositionsListResponse.class, code = 200, message = "Positions list")
    )
    public Response getPositions() {
        List<PositionEntity> positions = userService.findAllPositions();
        PositionsListResponse response = new PositionsListResponse(positions);
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @AllArgsConstructor
    @Getter
    public final class UsersListResponse {
        private final List<UserEntity> users;
    }

    @AllArgsConstructor
    @Getter
    public final class DepartmentsListResponse {
        private final List<DepartmentEntity> departments;
    }

    @AllArgsConstructor
    @Getter
    public final class PositionsListResponse {
        private final List<PositionEntity> positions;
    }
}
