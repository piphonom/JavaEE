package ru.otus.rik.web;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ru.otus.rik.domain.DepartmentEntity;
import ru.otus.rik.domain.PositionEntity;
import ru.otus.rik.domain.RoleEntity;
import ru.otus.rik.domain.UserEntity;
import ru.otus.rik.service.persistence.JpaPersistenceService;
import ru.otus.rik.service.persistence.PersistenceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@WebServlet("/DbInit")
public class DbInitServlet extends HttpServlet {
    PersistenceService persistenceService = new JpaPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dropTables();
        initDepartments();
        initPositions();
        initRoles();
    }

    private void dropTables() {
        List<UserEntity> users = persistenceService.findAllUsers();
        users.forEach(persistenceService::deleteUser);

        List<DepartmentEntity> departments = persistenceService.findAllDepartments();
        departments.forEach(persistenceService::deleteDepartment);

        List<PositionEntity> positions = persistenceService.findAllPositions();
        positions.forEach(persistenceService::deletePosition);

        List<RoleEntity> roles = persistenceService.findAllRoles();
        roles.forEach(persistenceService::deleteRole);
    }

    private void initDepartments() throws IOException {
        try (Reader in = new InputStreamReader(getServletContext().getResourceAsStream("/WEB-INF/Departments.csv"))) {
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);
            for (CSVRecord record : records) {
                DepartmentEntity department = new DepartmentEntity();
                department.setName(record.get("Name"));
                department.setLocation(record.get("Location"));
                persistenceService.saveDepartment(department);
            }
        }
    }

    private void initPositions() throws IOException {
        try (Reader in = new InputStreamReader(getServletContext().getResourceAsStream("/WEB-INF/Positions.csv"))) {
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);
            for (CSVRecord record : records) {
                PositionEntity position = new PositionEntity();
                position.setTitle(record.get("Title"));
                position.setSalary(Integer.valueOf(record.get("Salary")));
                persistenceService.savePosition(position);
            }
        }
    }

    private void initRoles() throws IOException {
        try (Reader in = new InputStreamReader(getServletContext().getResourceAsStream("/WEB-INF/Roles.csv"))) {
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);
            for (CSVRecord record : records) {
                RoleEntity role = new RoleEntity();
                role.setName(record.get("Name"));
                persistenceService.saveRole(role);
            }
        }
    }
}
