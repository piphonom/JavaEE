package ru.otus.rik.service.security;

import com.google.gwt.aria.client.Role;
import lombok.Getter;
import ru.otus.rikapi.entities.RoleEntity;
import ru.otus.rikapi.entities.UserEntity;
import ru.otus.rik.service.persistence.PersistenceService;
import ru.otus.rik.service.helpers.HashGenerator;
import ru.otus.rikapi.service.AuthenticationService;

import javax.ejb.*;
import javax.enterprise.context.SessionScoped;
import javax.security.sasl.AuthenticationException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
@Remote(AuthenticationService.class)
@LocalBean
//@SessionScoped
@Getter
public class AuthenticationServiceImpl implements AuthenticationService, Serializable {

    //private static final PersistenceService persistenceService = new JpaPersistenceService();
    @EJB
    private PersistenceService persistenceService;
    private volatile UserEntity currentUser;

    @Override
    public UserEntity authenticateByEmail(String email, String password) throws AuthenticationException {
        UserEntity user = persistenceService.findUserByEmail(email);
        if (user == null) {
            throw new AuthenticationException("User not found");
        }
        try {
            HashGenerator hashGenerator = new HashGenerator();
            String hash = hashGenerator.setSalt(user.getSalt()).update(password).digest();
            if (!hash.equals(user.getPwdHash())) {
                throw new AuthenticationException("Wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new AuthenticationException("Internal error");
        }
        currentUser = user;
        return user;
    }

    @Override
    public Set<String> getUserRoles(String email) {
        Set<String> roles = null;
        UserEntity user = persistenceService.findUserByEmail(email);
        if (user != null) {
            roles = user.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toSet());
        }
        return roles;
    }
}
