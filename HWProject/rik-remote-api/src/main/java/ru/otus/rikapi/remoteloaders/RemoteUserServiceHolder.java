package ru.otus.rikapi.remoteloaders;

import ru.otus.rikapi.service.UserService;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RemoteUserServiceHolder {

    private RemoteUserServiceHolder() {}

    public static UserService getUserService() {
        try {
            InitialContext context = new InitialContext();
            UserService userService = (UserService) context.lookup("java:global/rik-it-system/UserServiceImpl!ru.otus.rikapi.service.UserService");
            return userService;
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
