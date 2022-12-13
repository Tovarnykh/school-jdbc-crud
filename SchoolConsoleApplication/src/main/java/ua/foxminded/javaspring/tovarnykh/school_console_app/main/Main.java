package ua.foxminded.javaspring.tovarnykh.school_console_app.main;

import java.sql.SQLException;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.CommandProvider;

public class Main {

    public static void main(String[] args) {
        try {
            CommandProvider.executeCommand(0);
            CommandProvider.executeCommand(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}