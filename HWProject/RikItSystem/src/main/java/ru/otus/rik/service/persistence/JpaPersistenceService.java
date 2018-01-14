package ru.otus.rik.service.persistence;

import ru.otus.rik.domain.UserEntity;
import ru.otus.rik.service.persistence.dao.jpa.JpaUserDAO;

import javax.persistence.*;
import java.util.function.Function;

public class JpaPersistenceService implements PersistenceService {
    private static final String PERSISTENCE_UNIT_NAME = "RikPersistenceWithMySql";
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    @Override
    public UserEntity findUserByName(String name) {
        return runTransaction((entityManager -> new JpaUserDAO(entityManager).findByName(name)));
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        return runTransaction(entityManager -> new JpaUserDAO(entityManager).save(user));
    }

    @Override
    public void deleteUser(UserEntity user) {
        runTransaction(entityManager -> new JpaUserDAO(entityManager).delete(user));
    }

    private <T> T runTransaction(Function<EntityManager, T> function) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        try {
            manager.setFlushMode(FlushModeType.COMMIT);
            manager.getTransaction().begin();
            T result = function.apply(manager);
            manager.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            manager.getTransaction().rollback();
            return null;
        } finally {
            manager.close();
        }
    }
}
