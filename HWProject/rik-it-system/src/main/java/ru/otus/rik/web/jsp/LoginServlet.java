package ru.otus.rik.web.jsp;

import ru.otus.rikapi.entities.UserEntity;
import ru.otus.rik.service.persistence.PersistenceService;
import ru.otus.rikapi.service.AuthenticationService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.security.sasl.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private static final String LOGIN_JSP = "/login.jsp";
    private static final String USERS_JSP = "/search-result.jsp";

    @EJB
    private PersistenceService persistenceService;

    @EJB
    //@Inject
    private AuthenticationService authenticationService;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        UserEntity user;

        if (session == null || session.getAttribute("user") == null) {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            try {
                user = authenticationService.authenticateByEmail(email, password);
            } catch (AuthenticationException e) {
                forwardToLoginPage(req, resp, email, "Failed: " + e.getMessage());
                return;
            }
            session = req.getSession(true);
            session.setAttribute("user", email);
        } else {
            String email = (String) session.getAttribute("user");
            try {
                user = persistenceService.findUserByEmail(email);
                if (user == null)
                    throw new Exception();
            } catch (Exception e) {
                forwardToLoginPage(req, resp, null, null);
                return;
            }
        }

        /* TODO: get the list depending on user's rights */
        List<UserEntity> usersList = persistenceService.findAllUsers();
//        req.setAttribute("usersList", usersList);
        session.setAttribute("usersList", usersList);
        String contextPath = req.getContextPath();
        req.getRequestDispatcher(USERS_JSP).forward(req, resp);
//        resp.sendRedirect(req.getContextPath() + USERS_JSP);
    }

    private void forwardToLoginPage(HttpServletRequest req, HttpServletResponse resp, String email, String error) throws IOException, ServletException {
        req.setAttribute("error", error);
        req.setAttribute("email", email);
        req.getRequestDispatcher(LOGIN_JSP).forward(req, resp);
    }
}
