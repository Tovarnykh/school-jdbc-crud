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

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.CommandProvider;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.ConnectionAspect;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.DatabaseProperties;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.commands.Commands;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.commands.FindStudents;

class FindStudentsTest {

    private static Connection conn;

    @BeforeAll
    static void setUp() {
        try {
            Class.forName("org.h2.Driver");
            DatabaseProperties.readPropertyFile("testDatabaseProperties.properties");
            conn = DriverManager.getConnection(DatabaseProperties.getDriver(), DatabaseProperties.getUserName(),
                    DatabaseProperties.getPassword());
            CommandProvider.executeCommand(Commands.INIT);
            CommandProvider.executeCommand(Commands.GENERATE);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        conn.close();
    }

    @Test
    void execute_CheckIfAnyStudentsWereFound_True() throws Exception {
        Connection connection = ConnectionAspect.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FindStudents.QUERY);
        preparedStatement.setString(1, "Art");
        ResultSet resultSet = preparedStatement.executeQuery();
        assertTrue(resultSet.next());
    }

}
