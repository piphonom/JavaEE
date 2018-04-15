package ru.otus.rik.domain.action;

import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;
import ru.otus.rikapi.entities.UserEntity;
import ru.otus.rikapi.remoteloaders.RemoteUserServiceHolder;
import ru.otus.rikapi.service.UserService;

import javax.inject.Inject;

@Getter
@Setter
public class EditUser extends ActionSupport {
    //private UserService userService = RemoteUserServiceHolder.getUserService();
    @Inject
    private UserService userService;

    private String email;
    private String department;
    private String position;
    private String message;
    private String error;
    private UserEntity user;

    @Override
    public String execute() throws Exception {
        user = userService.findUserByEmail(email);
        if (user == null) {
            error = "User not found";
            return ERROR;
        }
        return SUCCESS;
    }
}
