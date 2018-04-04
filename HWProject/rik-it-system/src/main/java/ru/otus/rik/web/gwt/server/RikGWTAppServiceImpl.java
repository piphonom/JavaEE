package ru.otus.rik.web.gwt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.otus.rikapi.entities.UserEntity;
import ru.otus.rik.service.persistence.JpaPersistenceService;
import ru.otus.rik.service.persistence.PersistenceService;
import ru.otus.rik.service.helpers.HashGenerator;
import ru.otus.rik.web.gwt.client.service.RikGWTAppService;
import ru.otus.rik.web.gwt.shared.exception.AuthorizeException;
import ru.otus.rik.web.gwt.shared.model.LoginFormDTO;

import java.security.NoSuchAlgorithmException;

public class RikGWTAppServiceImpl extends RemoteServiceServlet implements RikGWTAppService {

    private static PersistenceService persistenceService = new JpaPersistenceService();

    @Override
    public void authorize(LoginFormDTO loginForm) throws AuthorizeException {
//        if (!ValidationRule.isValid(loginForm)) {
//            throw new AuthorizeException("Form filling is invalid");
//        }
        UserEntity user = persistenceService.findUserByEmail(loginForm.getLogin());
        if (user == null) {
            throw new AuthorizeException("User not found");
        }
        try {
            HashGenerator hashGenerator = new HashGenerator();
            String hash = hashGenerator.setSalt(user.getSalt()).update(loginForm.getPassword()).digest();
            if (!hash.equals(user.getPwdHash())) {
                throw new AuthorizeException("Wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new AuthorizeException("Internal error");
        }
    }
}
