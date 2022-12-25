package ua.foxminded.javaspring.tovarnykh.console_app.dao.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ua.foxminded.javaspring.tovarnykh.school_console_app.commands.CommandProvider;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.DatabaseProperties;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.statements.SelectGroup;

class FindGroupTest {

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
    void execute_CheckIfAnyGroupsWereFound_True() throws Exception {
        try (Statement statement = connection.createStatement()) {
            CachedRowSet cachedRowSet = SelectGroup.select(1);
            assertTrue(cachedRowSet.next());
        }
    }

}
