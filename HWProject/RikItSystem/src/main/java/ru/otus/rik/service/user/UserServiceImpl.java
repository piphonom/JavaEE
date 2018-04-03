package ru.otus.rik.service.user;

import ru.otus.rik.domain.DepartmentEntity;
import ru.otus.rik.domain.PositionEntity;
import ru.otus.rik.domain.UserEntity;
import ru.otus.rik.service.persistence.PersistenceService;
import ru.otus.rik.service.user.exceptions.UserCreationException;
import ru.otus.rik.service.user.exceptions.UserModificationException;
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
