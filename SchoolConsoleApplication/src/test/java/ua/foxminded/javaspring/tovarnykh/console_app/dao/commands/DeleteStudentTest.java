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

import ua.foxminded.javaspring.tovarnykh.school_console_app.commands.CommandProvider;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.DatabaseProperties;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.statements.InsertStudent;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.statements.DeleteStudentById;

class DeleteStudentTest {

    private static Connection connection;

    @BeforeAll
    static void setUp() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        DatabaseProperties.readPropertyFile("testDatabaseProperties.properties");
        connection = DriverManager.getConnection(DatabaseProperties.getDriver(), DatabaseProperties.getUserName(),
                DatabaseProperties.getPassword());
        CommandProvider.commandByCode.get(0).execute();
        CommandProvider.commandByCode.get(100).execute();
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        connection.close();
    }

    @Test
    void execute_CheckIsStudentWasDelete_False() throws Exception {
        InsertStudent.insert(1, "Adam", "Adamson");
        DeleteStudentById.delete(201);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT student_id FROM students WHERE student_id = 201");
            assertFalse(resultSet.next());
        }
    }

}
