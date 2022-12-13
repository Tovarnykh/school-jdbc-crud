package ua.foxminded.javaspring.tovarnykh.school_console_app.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import org.apache.ibatis.jdbc.ScriptRunner;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.ConnectionAspect;

import java.sql.SQLException;

public class InitializeDatabase implements Command {

    private static final String CREATE_TABLE_GROUPS = "createTableGroups.sql";
    private static final String CREATE_TABLE_STUDENTS = "createTableStudents.sql";
    private static final String CREATE_TABLE_COURSES = "createTableCourses.sql";

    List<String> scripts = List.of(CREATE_TABLE_GROUPS, CREATE_TABLE_COURSES, CREATE_TABLE_STUDENTS);

    @Override
    public void execute() throws SQLException {
        scripts.forEach(script -> {
            try (Connection connection = ConnectionAspect.getConnection();
                    Reader reader = new BufferedReader(new FileReader(
                            Thread.currentThread().getContextClassLoader().getResource("").getPath() + script));) {

                ScriptRunner scriptRunner = new ScriptRunner(connection);
                scriptRunner.runScript(reader);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                System.out.println("Such script was not found.");
            } catch (IOException e) {
                System.out.println("Cant open Reader class.");
            }
        });
    }

}
