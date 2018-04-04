package ru.otus.rik.service.helpers;

import ru.otus.rik.service.user.UserService;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RemoteUserServiceHolder {

    private RemoteUserServiceHolder() {}

    public static UserService getUserService() {
        try {
            InitialContext context = new InitialContext();
            UserService userService = (UserService) context.lookup("java:global/rik-it-system/UserServiceImpl!ru.otus.rik.service.user.UserService");
            return userService;
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
