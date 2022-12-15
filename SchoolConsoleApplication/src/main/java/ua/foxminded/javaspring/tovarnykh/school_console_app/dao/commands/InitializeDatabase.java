package ua.foxminded.javaspring.tovarnykh.school_console_app.dao.commands;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.util.List;
import org.apache.ibatis.jdbc.ScriptRunner;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.ConnectionAspect;

import java.sql.SQLException;

public class InitializeDatabase implements Command {

    List<String> executableScripts = List.of("createTableGroups.sql", "createTableStudents.sql",
            "createTableCourses.sql", "createTableStudents_Courses.sql");

    @Override
    public String execute() throws SQLException {
        executableScripts.forEach(script -> {
            try (Connection connection = ConnectionAspect.getConnection();
                    Reader reader = new BufferedReader(new FileReader(
                            Thread.currentThread().getContextClassLoader().getResource("").getPath() + script));) {

                ScriptRunner scriptRunner = new ScriptRunner(connection);
                scriptRunner.setLogWriter(null);
                scriptRunner.runScript(reader);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                System.out.println("Such script was not found.");
            } catch (IOException e) {
                System.out.println("Cant open Reader class.");
            }
        });
        new GenerateData().execute();
        return "true";
    }

}