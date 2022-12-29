package ua.foxminded.javaspring.tovarnykh.console_app.dao.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ua.foxminded.javaspring.tovarnykh.school_console_app.commands.Command;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.StudentsCoursesDAO;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.StudentsDAO;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.config.DatabaseProperties;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.fabric.FabricDAO;

class AddToCourseTest {

    private static Connection connection;

    @BeforeAll
    static void setUp() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        DatabaseProperties.readPropertyFile("testDatabaseProperties.properties");
        connection = DriverManager.getConnection(DatabaseProperties.getDriver(), DatabaseProperties.getUserName(),
                DatabaseProperties.getPassword());
        Command.INIT.getCommand().execute();
        Command.POPULATE.getCommand().execute();
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        connection.close();
    }

    @Test
    void execute_CheckIsStudentWasAddToCourse_True() throws Exception {
        StudentsDAO studentDAO = FabricDAO.getStudents();
        StudentsCoursesDAO studentsCoursesDAO = new StudentsCoursesDAO();
        
        studentDAO.insert(1, "Adam", "Adamson");
        studentsCoursesDAO.insert(201, 1);

        PreparedStatement checkStatement = connection
                .prepareStatement("SELECT * FROM students_courses WHERE student_id = 201 AND course_id = 1");
        ResultSet resultSet = checkStatement.executeQuery();
        assertTrue(resultSet.next());
    }

}
