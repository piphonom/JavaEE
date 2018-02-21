package ru.otus.rik.service.taglib;

import ru.otus.rik.domain.DepartmentEntity;
import ru.otus.rik.service.persistence.helpers.PersistenceServiceHolder;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

public class RikDepartmentsTagHandler extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        List<DepartmentEntity> departments = PersistenceServiceHolder.getPersistenceService().findAllDepartments();
        getJspContext().setAttribute("departmentsList", departments);
    }
}
