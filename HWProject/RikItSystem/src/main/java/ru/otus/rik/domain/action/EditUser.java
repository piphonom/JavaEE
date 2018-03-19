package ru.otus.rik.domain.action;

import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;
import ru.otus.rik.domain.UserEntity;
import ru.otus.rik.service.helpers.PersistenceServiceHolder;
import ru.otus.rik.service.persistence.PersistenceService;

@Getter
@Setter
public class EditUser extends ActionSupport {
    PersistenceService persistenceService = PersistenceServiceHolder.getPersistenceService();

    private String email;
    private String department;
    private String position;
    private String message;
    private String error;
    private UserEntity user;

    @Override
    public String execute() throws Exception {
        user = persistenceService.findUserByEmail(email);
        if (user == null) {
            error = "User not found";
            return ERROR;
        }
        return SUCCESS;
    }
}
