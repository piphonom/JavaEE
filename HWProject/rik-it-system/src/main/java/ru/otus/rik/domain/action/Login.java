package ru.otus.rik.domain.action;

import com.opensymphony.xwork2.ActionSupport;

import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import ru.otus.rikapi.entities.UserEntity;
import ru.otus.rikapi.remoteloaders.RemoteAuthenticationServiceHolder;
import ru.otus.rikapi.service.AuthenticationService;

import javax.inject.Inject;
import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Getter
@Setter
public class Login extends ActionSupport {

    private AuthenticationService authenticationService = RemoteAuthenticationServiceHolder.getAuthenticationService();
    //@Inject
    //private AuthenticationService authenticationService;

    private String email;
    private String password;
    private UserEntity user;
    private String error;

    @Override
    public String execute() throws Exception {

        /* TODO: no good idea to use session here */
//        HttpServletRequest request = ServletActionContext.getRequest();
//        HttpSession session = request.getSession(false);
//        if (session == null || session.getAttribute("user") == null) {
//        try {
//                user = authenticationService.authenticateByEmail(email, password);
//            } catch (AuthenticationException e) {
//                error = e.getMessage();
//                return ERROR;
//            }
//        }
//
//        session = request.getSession(true);
//        session.setAttribute("user", email);

        return SUCCESS;
    }
}
