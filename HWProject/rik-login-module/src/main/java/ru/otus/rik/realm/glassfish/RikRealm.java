package ru.otus.rik.realm.glassfish;

import com.sun.appserv.security.AppservRealm;
import com.sun.enterprise.security.auth.realm.BadRealmException;
import com.sun.enterprise.security.auth.realm.InvalidOperationException;
import com.sun.enterprise.security.auth.realm.NoSuchRealmException;
import com.sun.enterprise.security.auth.realm.NoSuchUserException;
import ru.otus.rikapi.remoteloaders.RemoteAuthenticationServiceHolder;
import ru.otus.rikapi.service.AuthenticationService;

import javax.security.sasl.AuthenticationException;
import java.util.*;

public class RikRealm extends AppservRealm {

    private static final String JAAS_CONTEXT = "jaas-context";

    @Override
    public void init(Properties properties) throws BadRealmException, NoSuchRealmException {
        System.out.println("Init RikRealm");
        String propJaasContext = properties.getProperty(JAAS_CONTEXT);
        if (propJaasContext != null) {
            setProperty(JAAS_CONTEXT, propJaasContext);
        }
    }

    @Override
    public String getAuthType() {
        return "RikRealm";
    }

    @Override
    public Enumeration getGroupNames(String username) throws InvalidOperationException, NoSuchUserException {
        AuthenticationService authenticationService = LazyAuthenticationServiceInitializer.authenticationService;
        if (authenticationService == null) {
            String message = "Failed to get auth service";
            System.out.println(message);
            throw new InvalidOperationException(message);
        }
        Set<String> roles = authenticationService.getUserRoles(username);
        if (roles == null) {
            roles = new HashSet<>();
        }
        return Collections.enumeration(roles);
    }

    public void authenticateUser(String username, String password) throws AuthenticationException {
        AuthenticationService authenticationService = LazyAuthenticationServiceInitializer.authenticationService;
        if (authenticationService == null) {
            String message = "Failed to get auth service";
            System.out.println(message);
            throw new AuthenticationException(message);
        }
        System.out.println("Used authentication service: " + authenticationService);
        authenticationService.authenticateByEmail(username, password);
    }

    private static class LazyAuthenticationServiceInitializer {
        private static final AuthenticationService authenticationService = RemoteAuthenticationServiceHolder.getAuthenticationService();
    }
}
