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

/**
 * @author Victor Tovarnykh
 * @version 0.15.0
 * @since 0.1.0
 */
public class InitializeDatabase implements Command {

    List<String> executableScripts = List.of("createTableGroups.sql", "createTableStudents.sql",
            "createTableCourses.sql", "createTableStudents_Courses.sql");

    /**
     * Method name: execute
     *
     * @return (String) String with marker that method executed successfully.
     * @throws SQLException
     *
     *                      Inside the function: 1. Read user`s input; 2. Filling
     *                      gaps in script with user`s data; 3. Send query to
     *                      database. sentence
     */
    public String execute() throws SQLException {
        executableScripts.forEach(script -> {
            try (Connection connection = ConnectionAspect.getConnection();
                    Reader reader = new BufferedReader(new FileReader(
                            Thread.currentThread().getContextClassLoader().getResource(script).getPath()));) {
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