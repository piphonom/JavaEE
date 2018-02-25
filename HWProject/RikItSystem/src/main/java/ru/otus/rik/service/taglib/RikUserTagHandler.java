package ru.otus.rik.service.taglib;

import lombok.Getter;
import lombok.Setter;
import ru.otus.rik.domain.UserEntity;
import ru.otus.rik.service.helpers.PersistenceServiceHolder;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

@Setter
@Getter
public class RikUserTagHandler extends SimpleTagSupport {
    private String email;

    @Override
    public void doTag() throws JspException, IOException {
        UserEntity user = null;
        if (email != null) {
            user = PersistenceServiceHolder.getPersistenceService().findUserByEmail(email);;
        }
        if (user != null) {
            getJspContext().setAttribute("user", user);
        }
    }
}
