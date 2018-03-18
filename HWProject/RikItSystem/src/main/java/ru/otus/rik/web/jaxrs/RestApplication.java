package ru.otus.rik.web.jaxrs;

import ru.otus.rik.web.jaxrs.credit.CreditCalculator;
import ru.otus.rik.web.jaxrs.user.UserService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api/v1")
public class RestApplication extends Application {
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>() {
            {
                add(CreditCalculator.class);
                add(UserService.class);
            }
        };
    }
}
