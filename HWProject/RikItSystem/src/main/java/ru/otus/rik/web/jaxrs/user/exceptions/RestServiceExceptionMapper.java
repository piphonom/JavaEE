package ru.otus.rik.web.jaxrs.user.exceptions;

import lombok.Getter;
import lombok.Setter;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.stream.Collectors;

@Provider
public class RestServiceExceptionMapper implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        Response.Status status = Response.Status.FORBIDDEN;
        if (exception instanceof ConstraintViolationException ||
                exception instanceof ResourceNotFoundException) {
            status = Response.Status.BAD_REQUEST;
        }
        return Response.status(status)
                .entity(new ExceptionRepresentation(exception))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @Getter
    @Setter
    public static final class ExceptionRepresentation {
        private static final String DESCRIPTION_ERRORS_DELIMITER = " | ";

        private String description;

        ExceptionRepresentation(Exception exception) {
            if (exception instanceof ConstraintViolationException) {
                if (((ConstraintViolationException) exception)
                        .getConstraintViolations() != null) {
                    this.description = ((ConstraintViolationException) exception)
                            .getConstraintViolations()
                            .stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.joining(DESCRIPTION_ERRORS_DELIMITER));
                } else {
                    this.description = exception.getLocalizedMessage();
                }
            } else {
                this.description = exception.getLocalizedMessage();
            }
        }
    }
}
