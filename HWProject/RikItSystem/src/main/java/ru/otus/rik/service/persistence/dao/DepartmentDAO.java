package ru.otus.rik.service.persistence.dao;

import ru.otus.rik.domain.DepartmentEntity;

import java.util.List;

public interface DepartmentDAO {
    DepartmentEntity findByName(String name);
    List<DepartmentEntity> findByLocation(String location);
    DepartmentEntity findByNameAndLocation(String name, String location);
    List<DepartmentEntity> findAll();
    DepartmentEntity save(DepartmentEntity department);
    DepartmentEntity delete(DepartmentEntity department);
}
