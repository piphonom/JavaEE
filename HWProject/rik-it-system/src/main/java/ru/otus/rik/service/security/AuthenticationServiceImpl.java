package ru.otus.rik.service.security;

import lombok.Getter;
import ru.otus.rikapi.entities.UserEntity;
import ru.otus.rik.service.persistence.PersistenceService;
import ru.otus.rik.service.helpers.HashGenerator;
import ru.otus.rikapi.service.AuthenticationService;

import javax.ejb.*;
import javax.security.sasl.AuthenticationException;
import java.security.NoSuchAlgorithmException;

@Stateful
@Remote(AuthenticationService.class)
@LocalBean
@Getter
public class AuthenticationServiceImpl implements AuthenticationService {

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
}
