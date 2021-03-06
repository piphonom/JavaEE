package ru.otus.rik.service.persistence.dao;

import ru.otus.rikapi.entities.PositionEntity;

import java.util.List;

public interface PositionDAO {
    PositionEntity findByTitle(String title);
    List<PositionEntity> findAll();
    PositionEntity save(PositionEntity position);
    PositionEntity delete(PositionEntity position);
}
