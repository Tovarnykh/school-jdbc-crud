package ua.foxminded.javaspring.tovarnykh.school_console_app.main;

import java.sql.SQLException;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.config.DatabaseProperties;

/**
*
* @author Victor Tovarnykh
* @version 0.15.0
* @since 0.1.0
*/
public class Main {
    
    /**
     * Method name: Main
     *
     * Entry point to program.
     */
    public static void main(String[] args) {
        try {
            DatabaseProperties.readPropertyFile("application.properties");
            ConsolePrinter console = new ConsolePrinter();
            console.exerciseChooser();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}