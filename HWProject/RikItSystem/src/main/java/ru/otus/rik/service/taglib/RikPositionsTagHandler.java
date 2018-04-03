package ru.otus.rik.service.taglib;

import ru.otus.rik.domain.PositionEntity;
import ru.otus.rik.service.user.UserService;

import javax.ejb.EJB;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

public class RikPositionsTagHandler extends SimpleTagSupport {

    @EJB
    private UserService userService;

    @Override
    public void doTag() throws JspException, IOException {
        List<PositionEntity> positions = userService.findAllPositions();
        getJspContext().setAttribute("positionsList", positions);
    }
}
