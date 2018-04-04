package ru.otus.rik.service.persistence.dao;

import ru.otus.rikapi.entities.RoleEntity;

import java.util.List;

public interface RoleDAO {
    RoleEntity findByName(String name);
    List<RoleEntity> findAll();
    RoleEntity save(RoleEntity role);
    RoleEntity delete(RoleEntity role);
}
