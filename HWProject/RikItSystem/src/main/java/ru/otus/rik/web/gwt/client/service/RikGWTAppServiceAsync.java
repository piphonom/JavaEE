package ru.otus.rik.web.gwt.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.otus.rik.web.gwt.shared.model.LoginFormDTO;

public interface RikGWTAppServiceAsync {
    void authorize(LoginFormDTO loginForm, AsyncCallback<Void> asyncCallback);
}
