package ru.otus.rik.domain.action;

import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;
import ru.otus.rik.domain.UserEntity;
import ru.otus.rik.service.helpers.RemoteUserServiceHolder;
import ru.otus.rik.service.user.UserService;

@Getter
@Setter
public class EditUser extends ActionSupport {
    private UserService userService = RemoteUserServiceHolder.getUserService();

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
