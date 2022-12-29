package ua.foxminded.javaspring.tovarnykh.school_console_app.dao.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Victor Tovarnykh
 * @version 0.15.0
 * @since 0.1.0
 */
public class ConnectionManager {

    /**
     * Method name: getConnection
     *
     * @return (Connection) Connection to the database.
     * @throws SQLException
     *
     *                      Inside the function: Connecting to database with url,
     *                      username and password from properties file.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DatabaseProperties.getDriver(), DatabaseProperties.getUserName(),
                DatabaseProperties.getPassword());
    }

    private ConnectionManager() {

    }

}