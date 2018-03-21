package ru.otus.rik.web.jaxrs.user;

import io.swagger.annotations.*;
import ru.otus.rik.domain.DepartmentEntity;
import ru.otus.rik.domain.PositionEntity;
import ru.otus.rik.domain.UserEntity;
import ru.otus.rik.service.helpers.PersistenceServiceHolder;
import ru.otus.rik.service.persistence.PersistenceService;
import ru.otus.rik.web.jaxrs.user.exceptions.ResourceNotFoundException;
import ru.otus.rik.web.jaxrs.user.parameters.CreateUserParameters;
import ru.otus.rik.web.jaxrs.user.parameters.UserEmailParameter;
import ru.otus.rik.web.jaxrs.user.parameters.EditUserParameters;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(tags={"rik_users"})
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserService {

    private final static PersistenceService persistenceService = PersistenceServiceHolder.getPersistenceService();

    /* TODO: move methods internals into separate service */

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation("Get user info")
    @ApiResponses({
        @ApiResponse(response = UserEntity.class, code = 200, message = "User created"),
        @ApiResponse(code = 403, message = "Bad request parameters")
    })
    public Response getUser(@Valid UserEmailParameter userEmailParameter) {
        UserEntity user = persistenceService.findUserByEmail(userEmailParameter.getEmail());
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).entity(user).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation("Create new user")
    @ApiResponses({
        @ApiResponse(code = 201, message = "User created"),
        @ApiResponse(code = 403, message = "Bad request parameters")
    })
    public Response createUser(@Valid CreateUserParameters createParameters) {
        /* TODO: to implement creation */
        return Response.status(Response.Status.CREATED).type(MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation("Edit existing user")
    @ApiResponses({
        @ApiResponse(code = 200, message = "User updated"),
        @ApiResponse(code = 403, message = "Bad request parameters")
    })
    public Response editUser(@Valid EditUserParameters editParameters) {
        UserEntity user = persistenceService.findUserByEmail(editParameters.getEmail());
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        if (editParameters.getDepartment() != null) {
            DepartmentEntity departmentEntity = persistenceService.findDepartmentByNameAndLocation(
                    editParameters.getDepartmentName(),
                    editParameters.getDepartmentLocation());
            if (departmentEntity == null) {
                throw new ResourceNotFoundException("Department not found");
            }
            user.setDepartmentRef(departmentEntity);
        }
        if (editParameters.getPosition() != null) {
            PositionEntity positionEntity = persistenceService.findPositionByTitle(editParameters.getPosition());
            if (positionEntity == null) {
                throw new ResourceNotFoundException("Position not found");
            }
            user.setPositionRef(positionEntity);
        }
        persistenceService.saveUser(user);

        return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation("Delete existing user")
    @ApiResponses({
        @ApiResponse(code = 200, message = "User deleted"),
        @ApiResponse(code = 403, message = "Bad request parameters")
    })
    public Response deleteUser(@Valid UserEmailParameter userEmailParameter) {
        UserEntity user = persistenceService.findUserByEmail(userEmailParameter.getEmail());
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        persistenceService.deleteUser(user);
        return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).build();
    }
}
