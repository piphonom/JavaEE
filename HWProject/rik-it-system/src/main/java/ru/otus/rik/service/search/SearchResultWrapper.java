package ru.otus.rik.service.search;

import lombok.Getter;
import ru.otus.rik.domain.UserEntity;
import ru.otus.rik.service.persistence.SearchParams;

import java.util.List;

@Getter
public final class SearchResultWrapper {
    private SearchParams searchParams;
    private List<UserEntity> result;

    SearchResultWrapper(SearchParams searchParams, List<UserEntity> result) {
        this.searchParams = searchParams;
        this.result = result;
    }
}
