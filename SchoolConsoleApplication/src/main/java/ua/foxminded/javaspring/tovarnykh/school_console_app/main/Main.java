package ua.foxminded.javaspring.tovarnykh.school_console_app.main;

import java.sql.SQLException;

public class Main {
    
    public static void main(String[] args) {
        try {
            ConsoleInterface consoleInterface = new ConsoleInterface();
            consoleInterface.exerciseChooser();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}