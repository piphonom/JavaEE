package ru.otus.rik.web.gwt.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.otus.rik.web.gwt.shared.exception.AuthorizeException;
import ru.otus.rik.web.gwt.shared.model.LoginFormDTO;


@RemoteServiceRelativePath("/RikGWTAppService")
public interface RikGWTAppService extends RemoteService {
    void authorize(LoginFormDTO loginForm) throws AuthorizeException;
}
