package ru.otus.rikapi.remoteloaders;

import ru.otus.rikapi.service.AuthenticationService;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RemoteAuthenticationServiceHolder {
    //private static final AuthenticationService authenticationService = new AuthenticationServiceImpl();

    private RemoteAuthenticationServiceHolder() {
    }

    public static AuthenticationService getAuthenticationService() {
        try {
            InitialContext context = new InitialContext();
            AuthenticationService authenticationService = (AuthenticationService) context.lookup("java:global/rik-it-system/AuthenticationServiceImpl!ru.otus.rikapi.service.AuthenticationService");
            return authenticationService;
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
