package ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.DatabaseProperties;

public class ConnectionAspect {

    private ConnectionAspect() {

    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DatabaseProperties.getDriver(), DatabaseProperties.getUserName(),
                DatabaseProperties.getPassword());
    }

}