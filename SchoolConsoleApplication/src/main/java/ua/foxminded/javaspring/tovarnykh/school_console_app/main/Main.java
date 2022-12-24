package ua.foxminded.javaspring.tovarnykh.school_console_app.main;

import java.sql.SQLException;

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
            ConsolePrinter console = new ConsolePrinter();
            console.exerciseChooser();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}