package ua.foxminded.javaspring.tovarnykh.console_app.dao.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import ua.foxminded.javaspring.tovarnykh.school_console_app.commands.Command;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.config.DatabaseProperties;

public abstract class DatabaseTest {

    protected static Connection connection;
    
    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        DatabaseProperties.readPropertyFile();
        connection = DriverManager.getConnection(DatabaseProperties.getDriver(), DatabaseProperties.getUserName(),
                DatabaseProperties.getPassword());
        Command.INIT.getCommand().execute();
        Command.POPULATE.getCommand().execute();
    }

    @AfterEach
    void tearDownAfterClass() throws Exception {
        connection.close();
    }
    
}