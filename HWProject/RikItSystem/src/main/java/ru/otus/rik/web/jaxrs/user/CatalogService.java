package ru.otus.rik.web.jaxrs.user;

import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.otus.rik.domain.DepartmentEntity;
import ru.otus.rik.domain.PositionEntity;
import ru.otus.rik.domain.UserEntity;
import ru.otus.rik.service.helpers.PersistenceServiceHolder;
import ru.otus.rik.service.persistence.PersistenceService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(tags={"rik_catalog"})
@Path("/catalog")
@Produces(MediaType.APPLICATION_JSON)
public class CatalogService {
    private final static PersistenceService persistenceService = PersistenceServiceHolder.getPersistenceService();

    @GET
    @Path("/users")
    @ApiOperation("Get users list")
    @ApiResponses(
            @ApiResponse(response = UsersListResponse.class, code = 200, message = "Users list")
    )
    public Response getUsers() {
        List<UserEntity> users = persistenceService.findAllUsers();
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
        List<DepartmentEntity> departments = persistenceService.findAllDepartments();
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
        List<PositionEntity> positions = persistenceService.findAllPositions();
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