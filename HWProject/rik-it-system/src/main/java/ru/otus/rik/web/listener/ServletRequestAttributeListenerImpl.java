package ru.otus.rik.web.listener;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import ru.otus.rikapi.entities.UserEntity;
import ru.otus.rik.service.persistence.SearchParams;
import ru.otus.rik.service.search.SearchResultWrapper;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static ru.otus.rik.service.search.SearchServiceImpl.SEARCH_CACHE_ATTRIBUTE_NAME;
import static ru.otus.rik.service.search.SearchServiceImpl.SEARCH_RESULT_ATTRIBUTE_NAME;

@WebListener
public class ServletRequestAttributeListenerImpl implements ServletRequestAttributeListener {



    private final static int CACHE_CAPACITY = 1000;
    private final static long LIFETIME_MINUTES = 1;

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        if (srae.getName().equals(SEARCH_RESULT_ATTRIBUTE_NAME)) {
            @SuppressWarnings("unchecked cast")
            Cache<SearchParams, List<UserEntity>> cache = (Cache<SearchParams, List<UserEntity>>)
                    srae.getServletContext().getAttribute(SEARCH_CACHE_ATTRIBUTE_NAME);

            if (cache == null) {
                cache = CacheBuilder.newBuilder()
                        .initialCapacity(CACHE_CAPACITY)
                        .expireAfterWrite(LIFETIME_MINUTES, TimeUnit.MINUTES)
                        .build();

                srae.getServletContext().setAttribute(SEARCH_CACHE_ATTRIBUTE_NAME, cache);
            }

            SearchResultWrapper wrapper = (SearchResultWrapper) srae.getValue();
            cache.put(wrapper.getSearchParams(), wrapper.getResult());
        }
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {

    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {

    }
}
