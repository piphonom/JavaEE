package ru.otus.rik.service.helpers;

import ru.otus.rik.service.chat.ChatService;
import ru.otus.rik.service.chat.ChatServiceImpl;
import ru.otus.rik.service.security.AuthenticationService;
import ru.otus.rik.service.security.AuthenticationServiceImpl;

public class AuthenticationServiceHolder {
    private static final AuthenticationService authenticationService = new AuthenticationServiceImpl();

    private AuthenticationServiceHolder() {
    }

    public static AuthenticationService getAuthenticationService() {
        return authenticationService;
    }
}
