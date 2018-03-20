package ru.otus.rik.web.jaxrs.user;

import ru.otus.rik.domain.DepartmentEntity;
import ru.otus.rik.domain.PositionEntity;
import ru.otus.rik.domain.UserEntity;
import ru.otus.rik.service.helpers.PersistenceServiceHolder;
import ru.otus.rik.service.persistence.PersistenceService;
import ru.otus.rik.web.jaxrs.user.exceptions.ResourceNotFoundException;
import ru.otus.rik.web.jaxrs.user.parameters.CreateUserParameters;
import ru.otus.rik.web.jaxrs.user.parameters.DeleteUserParameters;
import ru.otus.rik.web.jaxrs.user.parameters.EditUserParameters;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserService {

    private final static PersistenceService persistenceService = PersistenceServiceHolder.getPersistenceService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(@Valid CreateUserParameters createParameters) {
        /* TODO: to implement creation */
        return Response.status(Response.Status.CREATED).type(MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
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
    public Response deleteUser(@Valid DeleteUserParameters editParameters) {
        /* TODO: to implement deletion */
        return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).build();
    }
}
