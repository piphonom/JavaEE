package ru.otus.rik.web.listener;

import ru.otus.rik.domain.DepartmentEntity;
import ru.otus.rik.domain.PositionEntity;
import ru.otus.rik.domain.RoleEntity;
import ru.otus.rik.service.persistence.JpaPersistenceService;
import ru.otus.rik.service.persistence.PersistenceService;
import ru.otus.rik.service.xml.XmlBinder;
import ru.otus.rik.web.xml.Constants;
import ru.otus.rik.web.xml.UserEntitiesList;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    private static final PersistenceService persistenceService = new JpaPersistenceService();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            URL xsd = sce.getServletContext().getResource(Constants.SCHEMA_RESOURCE);
            File usersFile = new File(Constants.XML_ALL_USERS_FILE);
//            if (!usersFile.exists()) {
//                InputStream inputStream = sce.getServletContext().getResourceAsStream("/WEB-INF/users.xml");
//                usersFile = new File(inputStream);
//            }
            XmlBinder<UserEntitiesList> binder = new XmlBinder<>(UserEntitiesList.class);
            UserEntitiesList users = binder.fromXML(xsd, usersFile);
            users.forEach(user -> {
                /* Constraints will protect us from duplications */
                /* We should care about it manually */
                DepartmentEntity department = persistenceService.findDepartmentByNameAndLocation(user.getDepartmentRef().getName(), user.getDepartmentRef().getLocation());
                if (department != null) {
                    user.setDepartmentRef(department);
                }
                PositionEntity position = persistenceService.findPositionByTitle(user.getPositionRef().getTitle());
                if (position != null) {
                    user.setPositionRef(position);
                }
                RoleEntity role = persistenceService.findRoleByName(user.getRoleRef().getName());
                if (role != null) {
                    user.setRoleRef(role);
                }
                persistenceService.saveUser(user);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to deserialize Database");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        UserEntitiesList usersList = new UserEntitiesList(persistenceService.findAllUsers());
        XmlBinder<UserEntitiesList> binder = new XmlBinder<>(UserEntitiesList.class);
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(Constants.XML_ALL_USERS_FILE))) {
            binder.toXML(usersList, fileOutputStream);
        } catch (IOException | JAXBException e) {
            System.err.println("Failed to serialize Database");
            e.printStackTrace();
        }
        persistenceService.dropAll();
    }


}
