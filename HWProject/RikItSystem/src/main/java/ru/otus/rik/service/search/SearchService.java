package ru.otus.rik.service.search;

import ru.otus.rik.domain.UserEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SearchService {
    List<UserEntity> findUserByParams(HttpServletRequest request);
}
