package ru.otus.rik.service.persistence.dao.jpa;

import ru.otus.rikapi.entities.DepartmentEntity;
import ru.otus.rik.service.persistence.dao.DepartmentDAO;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class JpaDepartmentDAO implements DepartmentDAO {

    private final EntityManager entityManager;
    private final JpaBaseDAO<DepartmentEntity> baseDAO;

    public JpaDepartmentDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.baseDAO = new JpaBaseDAO<>(entityManager, DepartmentEntity.class);
    }

    @Override
    public DepartmentEntity findByName(String name) {
        return baseDAO.findSingleResultByOneParameter("name", name);
    }

    @Override
    public List<DepartmentEntity> findByLocation(String location) {
        return baseDAO.findByOneParameter("location", location);
    }

    @Override
    public DepartmentEntity findByNameAndLocation(String name, String location) {
        Query query = entityManager.createQuery("from DepartmentEntity d where d.name like :name and d.location like :location")
                .setParameter("name", name)
                .setParameter("location", location)
                .setMaxResults(1);

        return (DepartmentEntity) query.getSingleResult();
    }

    @Override
    public List<DepartmentEntity> findAll() {
        return baseDAO.findAll();
    }

    @Override
    public DepartmentEntity save(DepartmentEntity department) {
        return baseDAO.save(department);
    }

    @Override
    public DepartmentEntity delete(DepartmentEntity department) {
        return baseDAO.delete(department);
    }
}
