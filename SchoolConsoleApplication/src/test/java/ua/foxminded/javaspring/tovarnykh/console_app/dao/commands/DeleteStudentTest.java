package ua.foxminded.javaspring.tovarnykh.console_app.dao.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ua.foxminded.javaspring.tovarnykh.school_console_app.commands.Command;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.StudentsDAO;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.config.DatabaseProperties;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.fabric.FabricDAO;

class DeleteStudentTest {

    private static Connection connection;

    @BeforeAll
    static void setUp() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        DatabaseProperties.readPropertyFile("application.properties");
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
    void execute_CheckIsStudentWasDelete_False() throws Exception {
        StudentsDAO studentDAO = FabricDAO.getStudents();
        
        studentDAO.insert(1, "Adam", "Adamson");
        studentDAO.delete(201);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT student_id FROM students WHERE student_id = 201");
            assertFalse(resultSet.next());
        }
    }

}
