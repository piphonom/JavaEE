package ru.otus.rik.service.taglib;

import lombok.Getter;
import lombok.Setter;
import ru.otus.rikapi.entities.UserEntity;
import ru.otus.rikapi.service.UserService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

@Setter
@Getter
public class RikUserTagHandler extends SimpleTagSupport {
    private String email;

    //@EJB
    @Inject
    private UserService userService;

    @Override
    public void doTag() throws JspException, IOException {
        UserEntity user = null;
        if (email != null) {
            user = userService.findUserByEmail(email);
        }
        if (user != null) {
            getJspContext().setAttribute("user", user);
        }
    }
}
