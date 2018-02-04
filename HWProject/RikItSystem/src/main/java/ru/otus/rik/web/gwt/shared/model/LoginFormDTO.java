package ru.otus.rik.web.gwt.shared.model;

import com.google.gwt.user.client.rpc.IsSerializable;

import javax.validation.constraints.NotNull;


public class LoginFormDTO implements IsSerializable {
    @NotNull
    private String login;
    @NotNull
    private String password;

    public LoginFormDTO() {
    }

    public LoginFormDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
