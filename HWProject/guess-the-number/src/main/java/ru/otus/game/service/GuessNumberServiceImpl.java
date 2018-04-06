package ru.otus.game.service;

import ru.otus.game.model.UserEntity;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Random;
import java.util.concurrent.Future;

import static javax.ejb.TransactionAttributeType.MANDATORY;
import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

@Stateless
@TransactionManagement
public class GuessNumberServiceImpl implements GuessNumberService {

    private static final String FIND_USER_TEMPLATE_FORMAT = "from %s t where t.name like :name";

    @PersistenceContext(name = "jpaDataSource")
    private EntityManager entityManager;

    private Random randomGenerator = new Random(System.currentTimeMillis());

    @Asynchronous
    public Future<Boolean> guessTheNumber(String userName, int number) {
        UserEntity user = findUser(userName);
        if (user == null) {
            user = createUser(userName);
        }

        int random = randomGenerator.nextInt(BOUND);

        boolean result = (random == number);
        if (result) {
            user.setAttempts(0);
        } else {
            user.setAttempts(user.getAttempts() + 1);
        }
        saveUser(user);

        if (user.getAttempts() != 0 && user.getAttempts() % MAX_ATTEMPTS == 0) {
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {}
        }

        return new AsyncResult<Boolean>(result);
    }

    @TransactionAttribute(MANDATORY)
    private UserEntity findUser(String name) {
        String select = String.format(FIND_USER_TEMPLATE_FORMAT, UserEntity.class.getSimpleName());
        Query query = entityManager.createQuery(select)
                .setParameter("name", name)
                .setMaxResults(1);
        UserEntity user = null;
        try {
            user = (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {

        }
        return user;
    }

    @TransactionAttribute(MANDATORY)
    private UserEntity createUser(String name) {
        UserEntity user = new UserEntity();
        user.setName(name);
        entityManager.persist(user);
        return user;
    }

    @TransactionAttribute(REQUIRES_NEW)
    private UserEntity saveUser(UserEntity user) {
        return entityManager.merge(user);
    }
}
