package ru.otus.rik.web.html;

import ru.otus.rik.domain.UserEntity;
import ru.otus.rik.service.persistence.JpaPersistenceService;
import ru.otus.rik.service.persistence.PersistenceService;
import ru.otus.rik.service.security.AuthenticationService;
import ru.otus.rik.service.security.AuthenticationServiceImpl;

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
    private static final String USERS_JSP = "/users.jsp";
    private static final AuthenticationService authenticationService = new AuthenticationServiceImpl();
    private static final PersistenceService persistenceService = new JpaPersistenceService();

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
                redirectToLoginPage(req, resp, email, "Failed: " + e.getMessage());
                return;
            }
            session = req.getSession(true);
            session.setAttribute("user", email);
        } else {
            String email = (String) session.getAttribute("user");
            try {
                user = persistenceService.findUserByEmail(email);
            } catch (Exception e) {
                redirectToLoginPage(req, resp, null, null);
                return;
            }
        }

        /* TODO: get the list depending on user's rights */
        List<UserEntity> usersList = persistenceService.findAllUsers();
        req.setAttribute("usersList", usersList);
        req.getRequestDispatcher(req.getContextPath() + USERS_JSP).forward(req, resp);
    }

    private void redirectToLoginPage(HttpServletRequest req, HttpServletResponse resp, String email, String error) throws IOException, ServletException {
        req.setAttribute("error", error);
        req.setAttribute("email", email);
        req.getRequestDispatcher(req.getContextPath() + LOGIN_JSP).forward(req, resp);
    }
}
