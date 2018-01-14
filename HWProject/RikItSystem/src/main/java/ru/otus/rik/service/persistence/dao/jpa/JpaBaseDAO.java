package ru.otus.rik.service.persistence.dao.jpa;

import ru.otus.rik.domain.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class JpaBaseDAO<T> {

    private static final String ONE_PARAMETER_QUERY_INITIAL_FORMAT = "from %s t where t.%s like :value";
    private final String entityClassName;
    private EntityManager entityManager;

    public JpaBaseDAO(EntityManager entityManager, Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClassName = entityClass.getSimpleName();
    }

    public T findByOneStringParameter(String parameterName, String value) {
        String queryHql = String.format(ONE_PARAMETER_QUERY_INITIAL_FORMAT, entityClassName, parameterName);

        Query query = entityManager.createQuery(queryHql)
                .setParameter("value", value)
                .setMaxResults(1);
        return (T) query.getSingleResult();
    }

    public T save(T object) {
        return entityManager.merge(object);
    }

    public void delete(T object) {
        entityManager.remove(object);
    }
}
