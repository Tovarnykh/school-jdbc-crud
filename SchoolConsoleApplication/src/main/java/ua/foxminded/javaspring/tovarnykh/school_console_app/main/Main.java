package ua.foxminded.javaspring.tovarnykh.school_console_app.main;

import java.sql.SQLException;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.CreateTables;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.InsertRandomData;

public class Main {

    public static void main(String[] args) {
        CreateTables com = new CreateTables();
        InsertRandomData ran = new InsertRandomData();
        try {
            com.execute();
            ran.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}