package ru.otus.rik.service.user;

import ru.otus.rikapi.entities.DepartmentEntity;
import ru.otus.rikapi.entities.PositionEntity;
import ru.otus.rikapi.entities.UserEntity;
import ru.otus.rik.service.persistence.PersistenceService;
import ru.otus.rikapi.service.UserService;
import ru.otus.rikapi.service.exceptions.UserCreationException;
import ru.otus.rikapi.service.exceptions.UserModificationException;
import ru.otus.rik.web.jaxrs.user.exceptions.ResourceNotFoundException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;


@Stateless
@Remote(UserService.class)
@LocalBean
public class UserServiceImpl implements UserService {
    @EJB
    private PersistenceService persistenceService;

    @Override
    public UserEntity findUserByEmail(String email) {
        return persistenceService.findUserByEmail(email);
    }

    @Override
    public UserEntity createUser(String email, String location, String department, String position) throws UserCreationException {
        return null;
    }

    @Override
    public UserEntity editUser(String email, String location, String department, String position) throws UserModificationException {
        UserEntity user = persistenceService.findUserByEmail(email);
        if (user == null) {
            throw new UserModificationException("User not found");
        }
        if (department != null && location != null) {
            DepartmentEntity departmentEntity = persistenceService.findDepartmentByNameAndLocation(department, location);
            if (departmentEntity == null) {
                throw new UserModificationException("Department not found");
            }
            user.setDepartmentRef(departmentEntity);
        }
        if (position != null) {
            PositionEntity positionEntity = persistenceService.findPositionByTitle(position);
            if (positionEntity == null) {
                throw new UserModificationException("Position not found");
            }
            user.setPositionRef(positionEntity);
        }
        return persistenceService.saveUser(user);
    }

    @Override
    public void deleteUser(String email) throws UserModificationException {
        UserEntity user = persistenceService.findUserByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        persistenceService.deleteUser(user);
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return persistenceService.findAllUsers();
    }

    @Override
    public List<DepartmentEntity> findAllDepartments() {
        return persistenceService.findAllDepartments();
    }

    @Override
    public List<PositionEntity> findAllPositions() {
        return persistenceService.findAllPositions();
    }

    @Override
    public double getAverageSalary() {
        return persistenceService.getAverageSalary();
    }
}
