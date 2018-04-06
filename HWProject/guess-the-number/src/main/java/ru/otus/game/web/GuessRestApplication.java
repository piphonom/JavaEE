package ru.otus.game.web;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api/v1")
public class GuessRestApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>() {
                {
                    add(GuessRestService.class);
                    add(RestServiceExceptionMapper.class);
                }
        };
    }
}
