package ru.otus.rik.service.taglib;

import ru.otus.rik.domain.PositionEntity;
import ru.otus.rik.service.persistence.helpers.PersistenceServiceHolder;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

public class RikPositionsTagHandler extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        List<PositionEntity> positions = PersistenceServiceHolder.getPersistenceService().findAllPositions();
        getJspContext().setAttribute("positionsList", positions);
    }
}
