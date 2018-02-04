package ru.otus.rik.web.gwt.shared.model;

import java.util.LinkedList;
import java.util.List;

public class UsersListDTO {
    private List<UserDTO> users = new LinkedList<>();

    public void addUser(UserDTO user) {
        users.add(user);
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}
