package ua.foxminded.javaspring.tovarnykh.console_app.dao.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.StudentsCoursesDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.StudentsDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.fabric.FabricDao;

class StudentsDaoTest extends DatabaseTest {

    @Test
    void insert_CheckIsStudentWasAdd_True() throws SQLException, ClassNotFoundException {
        StudentsDao studentDAO = FabricDao.getStudentsDao();
        studentDAO.insert(1, "Adam", "Adamson");
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement
                        .executeQuery("SELECT first_name FROM students WHERE first_name = 'Adam'")) {
            assertTrue(resultSet.next());
        }
    }

    @Test
    void delete_CheckIsStudentWasDelete_True() throws Exception {
        StudentsDao studentDAO = FabricDao.getStudentsDao();

        studentDAO.insert(1, "Rosman", "Dty");
        studentDAO.delete(202);
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement
                        .executeQuery("SELECT first_name FROM students WHERE first_name = 'Rosman'")) {
            while (resultSet.next()) {
                assertEquals("Rosman", resultSet.getString("first_name"));
            }

        }
    }

    @Test
    void delete_CheckIsStudentWasDeleteEnrolledToCourse_True() throws Exception {
        StudentsDao studentDAO = FabricDao.getStudentsDao();
        StudentsCoursesDao studentsCoursesDAO = new StudentsCoursesDao();

        studentDAO.insert(1, "Brand", "Dend");
        studentsCoursesDAO.insert(201, 1);
        studentDAO.delete(201);
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement
                        .executeQuery("SELECT first_name FROM students WHERE first_name = 'Brand'");) {
            while (resultSet.next()) {
                assertEquals("Brand", resultSet.getString("first_name"));
            }
        }
    }

}
