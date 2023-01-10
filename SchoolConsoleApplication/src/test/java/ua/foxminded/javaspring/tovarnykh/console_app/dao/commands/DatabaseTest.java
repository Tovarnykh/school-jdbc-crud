package ua.foxminded.javaspring.tovarnykh.console_app.dao.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import ua.foxminded.javaspring.tovarnykh.school_console_app.commands.Command;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.config.DatabaseProperties;

public abstract class DatabaseTest {

    protected static Connection connection;

    @BeforeAll
    static void setUp() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        DatabaseProperties.readPropertyFile();
        connection = DriverManager.getConnection(DatabaseProperties.getDriver(), DatabaseProperties.getUserName(),
                DatabaseProperties.getPassword());
        Command.INIT.getCommand().execute();
        Command.POPULATE.getCommand().execute();
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        connection.close();
    }

}