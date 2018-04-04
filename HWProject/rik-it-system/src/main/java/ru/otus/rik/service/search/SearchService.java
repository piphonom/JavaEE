package ru.otus.rik.service.search;

import ru.otus.rikapi.entities.UserEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SearchService {
    List<UserEntity> findUserByParams(HttpServletRequest request);
}
