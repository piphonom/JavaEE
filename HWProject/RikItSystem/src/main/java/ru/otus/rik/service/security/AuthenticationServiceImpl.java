package ru.otus.rik.service.security;

import lombok.Getter;
import ru.otus.rik.domain.UserEntity;
import ru.otus.rik.service.persistence.JpaPersistenceService;
import ru.otus.rik.service.persistence.PersistenceService;
import ru.otus.rik.service.helpers.HashGenerator;

import javax.security.sasl.AuthenticationException;
import java.security.NoSuchAlgorithmException;

@Getter
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final PersistenceService persistenceService = new JpaPersistenceService();
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
