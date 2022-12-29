package ua.foxminded.javaspring.tovarnykh.console_app.dao.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ua.foxminded.javaspring.tovarnykh.school_console_app.commands.Command;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.GroupsDAO;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.config.DatabaseProperties;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.fabric.FabricDAO;

class FindGroupTest {

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
    void execute_CheckIfAnyGroupsWereFound_True() throws Exception {
        GroupsDAO groupsDAO = FabricDAO.getGroups();
        CachedRowSet cachedRowSet = groupsDAO.select(1);
        assertTrue(cachedRowSet.next());
    }

}
