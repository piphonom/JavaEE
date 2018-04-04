package ru.otus.rik.web.jaxrs.user;

import io.swagger.annotations.*;
import ru.otus.rikapi.entities.UserEntity;
import ru.otus.rikapi.service.UserService;
import ru.otus.rikapi.service.exceptions.UserModificationException;
import ru.otus.rik.web.jaxrs.user.exceptions.ResourceNotFoundException;
import ru.otus.rik.web.jaxrs.user.parameters.CreateUserParameters;
import ru.otus.rik.web.jaxrs.user.parameters.UserEmailParameter;
import ru.otus.rik.web.jaxrs.user.parameters.EditUserParameters;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(tags={"rik_users"})
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserRestService {

    @EJB
    private UserService userService;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation("Get user info")
    @ApiResponses({
        @ApiResponse(response = UserEntity.class, code = 200, message = "User created"),
        @ApiResponse(code = 403, message = "Bad request parameters")
    })
    public Response getUser(@Valid UserEmailParameter userEmailParameter) {
        UserEntity user = userService.findUserByEmail(userEmailParameter.getEmail());
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
        try {
            userService.editUser(
                    editParameters.getEmail(),
                    editParameters.getDepartmentLocation(),
                    editParameters.getDepartmentName(),
                    editParameters.getPosition());
        } catch (UserModificationException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
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
        try {
            userService.deleteUser(userEmailParameter.getEmail());
        } catch (UserModificationException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
        return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).build();
    }
}
