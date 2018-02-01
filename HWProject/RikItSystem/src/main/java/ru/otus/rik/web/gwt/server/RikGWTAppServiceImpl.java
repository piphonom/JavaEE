package ru.otus.rik.web.gwt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.otus.rik.web.gwt.client.service.RikGWTAppService;
import ru.otus.rik.web.gwt.shared.model.LoginFormDTO;

public class RikGWTAppServiceImpl extends RemoteServiceServlet implements RikGWTAppService {
    @Override
    public void authorize(LoginFormDTO loginForm) {

    }
}
