package ru.otus.rik.service.helpers;

import ru.otus.rik.service.chat.ChatService;
import ru.otus.rik.service.chat.ChatServiceImpl;
import ru.otus.rik.service.persistence.PersistenceService;
import ru.otus.rik.service.security.AuthenticationService;
import ru.otus.rik.service.security.AuthenticationServiceImpl;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class AuthenticationServiceHolder {
    //private static final AuthenticationService authenticationService = new AuthenticationServiceImpl();

    private AuthenticationServiceHolder() {
    }

    public static AuthenticationService getAuthenticationService() {
        try {
            InitialContext context = new InitialContext();
            AuthenticationService authenticationService = (AuthenticationService) context.lookup("java:global/rik-it-system/AuthenticationServiceImpl!ru.otus.rik.service.security.AuthenticationService");
            return authenticationService;
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
