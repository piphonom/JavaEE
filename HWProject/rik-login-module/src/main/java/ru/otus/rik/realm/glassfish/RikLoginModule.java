package ru.otus.rik.realm.glassfish;

import com.sun.appserv.security.AppservPasswordLoginModule;

import javax.security.auth.login.LoginException;
import javax.security.sasl.AuthenticationException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class RikLoginModule extends AppservPasswordLoginModule {
    @Override
    protected void authenticateUser() throws LoginException {
        System.out.println("Auth user " + _username + ", password " + _passwd);

        if (!(_currentRealm instanceof RikRealm)) {
            System.out.println("Bad realm used " + _currentRealm);
            throw new LoginException("Bad realm");
        }

        RikRealm realm = (RikRealm)_currentRealm;

        try {
            realm.authenticateUser(_username, new String(_passwd));
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("Bad auth for " + _username + " with password" + _passwd);
            throw new LoginException("Bad user name or password");
        }

        try {
            List<String> groups = Collections.list(realm.getGroupNames(_username));
            //commitUserAuthentication(Stream.of("ADMIN").toArray(String[]::new));
            commitUserAuthentication(groups.stream().toArray(String[]::new));
        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
            throw new LoginException("Login error");
        }
    }
}
