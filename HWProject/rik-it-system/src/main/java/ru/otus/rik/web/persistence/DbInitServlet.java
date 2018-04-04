package ru.otus.rik.web.persistence;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ru.otus.rik.domain.DepartmentEntity;
import ru.otus.rik.domain.PositionEntity;
import ru.otus.rik.domain.RoleEntity;
import ru.otus.rik.domain.UserEntity;
import ru.otus.rik.service.persistence.JpaPersistenceService;
import ru.otus.rik.service.persistence.PersistenceService;
import ru.otus.rik.service.helpers.HashGenerator;
import ru.otus.rik.service.helpers.RandomString;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@WebServlet("/DbInit")
public class DbInitServlet extends HttpServlet {
    PersistenceService persistenceService = new JpaPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        persistenceService.dropAll();
        initDepartments();
        initPositions();
        initRoles();

        try (PrintWriter writer = resp.getWriter()) {
            List<UserEntity> unsaved = initUsers();
            if (unsaved.size() != 0) {
                writer.println("Unsaved users:");
                unsaved.forEach(user -> writer.println(user.getName()));
            } else {
                writer.println("Everything is saved");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new ServletException(e.getCause());
        }
    }

    private void initDepartments() throws IOException {
        processCsv("/WEB-INF/csv/Departments.csv", (record) -> {
            DepartmentEntity department = new DepartmentEntity();
            department.setName(record.get("Name"));
            department.setLocation(record.get("Location"));
            persistenceService.saveDepartment(department);
        });
    }

    private void initPositions() throws IOException {
        processCsv("/WEB-INF/csv/Positions.csv", (record) -> {
            PositionEntity position = new PositionEntity();
            position.setTitle(record.get("Title"));
            position.setSalary(Integer.valueOf(record.get("Salary")));
            persistenceService.savePosition(position);
        });
    }

    private void initRoles() throws IOException {
        processCsv("/WEB-INF/csv/Roles.csv", (record) -> {
            RoleEntity role = new RoleEntity();
            role.setName(record.get("Name"));
            persistenceService.saveRole(role);
        });
    }

    private List<UserEntity> initUsers() throws IOException, NoSuchAlgorithmException {
        List<UserEntity> unsavedUsers = new ArrayList<>();
        RandomString randomString = new RandomString();
        HashGenerator hashGenerator = new HashGenerator();
        /* Name,Email,Phone,DepartmentName,DepartmentLocation,Position,Role,Password */
        processCsv("/WEB-INF/csv/Users.csv", (record) -> {
            UserEntity user = new UserEntity();
            user.setName(record.get("Name"));
            user.setEmail(record.get("Email"));
            user.setPhone(record.get("Phone"));
            String password = record.get("Password");
            if (password != null) {
                String salt = randomString.nextString();
                String hash = hashGenerator.setSalt(salt).update(password).digest();
                user.setSalt(salt);
                user.setPwdHash(hash);
            }

            DepartmentEntity department = persistenceService.findDepartmentByNameAndLocation(record.get("DepartmentName"), record.get("DepartmentLocation"));
            PositionEntity position = persistenceService.findPositionByTitle(record.get("Position"));
            RoleEntity role = persistenceService.findRoleByName(record.get("Role"));

            if (department == null ||
                    position == null ||
                    role == null) {
                unsavedUsers.add(user);
                return;
            }
            user.setDepartmentRef(department);
            user.setPositionRef(position);
            user.setRoleRef(role);

            if (persistenceService.saveUser(user) == null) {
                unsavedUsers.add(user);
            }
        });
        return unsavedUsers;
    }

    private void processCsv(String resourcePath, Consumer<CSVRecord> recordProcessor) throws IOException {
        try (Reader in = new InputStreamReader(getServletContext().getResourceAsStream(resourcePath))) {
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);
            records.forEach(recordProcessor);
        }
    }
}
