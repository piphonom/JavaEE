package ru.otus.rik.service.persistence.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class JpaBaseDAO<T> {

    private static final String ONE_PARAMETER_QUERY_INITIAL_FORMAT = "from %s t where t.%s like :value";
    private static final String ALL_PARAMETER_QUERY_INITIAL_FORMAT = "from %s t";

    private EntityManager entityManager;
    private final String entityClassName;
    private final String queryHqlSelectAllTemplate;

    public JpaBaseDAO(EntityManager entityManager, Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClassName = entityClass.getSimpleName();
        this.queryHqlSelectAllTemplate = String.format(ALL_PARAMETER_QUERY_INITIAL_FORMAT, entityClassName);
    }

    public T findSingleResultByOneParameter(String parameterName, String value) {
        String queryHql = String.format(ONE_PARAMETER_QUERY_INITIAL_FORMAT, entityClassName, parameterName);

        Query query = entityManager.createQuery(queryHql)
                .setParameter("value", value)
                .setMaxResults(1);

        return (T) query.getSingleResult();
    }

    public List<T> findByOneParameter(String parameterName, String value) {
        String queryHql = String.format(ONE_PARAMETER_QUERY_INITIAL_FORMAT, entityClassName, parameterName);

        Query query = entityManager.createQuery(queryHql)
                .setParameter("value", value);

        return query.getResultList();
    }

    public List<T> findAll() {
        Query query = entityManager.createQuery(queryHqlSelectAllTemplate);
        return query.getResultList();
    }

    public T save(T object) {
        return entityManager.merge(object);
    }

    public T delete(T object) {
        if (!entityManager.contains(object)) {
            object = entityManager.merge(object);
        }
        entityManager.remove(object);
        return object;
    }
}
