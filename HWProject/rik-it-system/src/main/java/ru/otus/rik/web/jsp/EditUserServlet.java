package ru.otus.rik.web.jsp;

import ru.otus.rikapi.entities.DepartmentEntity;
import ru.otus.rikapi.entities.PositionEntity;
import ru.otus.rikapi.entities.UserEntity;
import ru.otus.rik.service.persistence.PersistenceService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditUserServlet", urlPatterns = "/edit")
public class EditUserServlet extends HttpServlet {

    private static final String USERS_JSP = "/search-result.jsp";

    @EJB
    private PersistenceService persistenceService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            if (action.equals("edit")) {
                String emailParameter = req.getParameter("email");
                String departmentNameParameter = req.getParameter("department");
                String positionTitleParameter = req.getParameter("position");

                if (emailParameter == null ||
                        departmentNameParameter == null ||
                        positionTitleParameter == null) {
                    forwardToUsersPage(req, resp, null, "Wrong request parameters");
                    return;
                }

                UserEntity user = persistenceService.findUserByEmail(emailParameter);
                if (user == null) {
                    forwardToUsersPage(req, resp, null, "User not found");
                    return;
                }

                String error = changeDepartment(user, departmentNameParameter);
                if (error != null) {
                    forwardToUsersPage(req, resp, null, error);
                    return;
                }

                error = changePosition(user, positionTitleParameter);
                if (error != null) {
                    forwardToUsersPage(req, resp, null, error);
                    return;
                }

                persistenceService.saveUser(user);
                updateSession(req.getSession(false), user);
                forwardToUsersPage(req, resp, "User was updated", null);
            }
        }
    }

    private String[] parseDepartmentParameter(String parameterValue) {
        return parameterValue.split("&");
    }

    private String changeDepartment(UserEntity user, String departmentNameParameter) {
        String[] departmentDesc = parseDepartmentParameter(departmentNameParameter);
        if (departmentDesc.length != 2) {
            return "Wrong department parameter";
        }
        DepartmentEntity departmentEntity = persistenceService.findDepartmentByNameAndLocation(departmentDesc[1], departmentDesc[0]);
        if (departmentEntity == null) {
             return "Department not found";
        }
        user.setDepartmentRef(departmentEntity);
        return null;
    }

    private String changePosition(UserEntity user, String positionTitleParameter) {
        PositionEntity positionEntity = persistenceService.findPositionByTitle(positionTitleParameter);
        if (positionEntity == null) {
            return "Position not found";
        }
        user.setPositionRef(positionEntity);
        return null;
    }

    private void updateSession(HttpSession session, UserEntity user) {
        if (session == null)
            return;
        List<UserEntity> usersList = (List<UserEntity>)session.getAttribute("usersList");
        if (usersList == null)
            return;

        usersList.replaceAll(u -> (u.getEmail().equals(user.getEmail())) ? user : u);
    }

    private void forwardToUsersPage(HttpServletRequest req, HttpServletResponse resp, String message, String error) throws IOException, ServletException {
        req.setAttribute("error", error);
        req.setAttribute("message", message);
        req.getRequestDispatcher(req.getContextPath() + USERS_JSP).forward(req, resp);
    }
}
