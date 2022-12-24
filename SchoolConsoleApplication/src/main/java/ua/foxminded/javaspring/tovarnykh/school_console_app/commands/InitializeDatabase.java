package ua.foxminded.javaspring.tovarnykh.school_console_app.commands;

import java.util.List;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.statements.CreateTables;

import java.sql.SQLException;

/**
 * @author Victor Tovarnykh
 * @version 0.15.0
 * @since 0.1.0
 */
public class InitializeDatabase implements Command {

    List<String> executableScripts = List.of("/createTableGroups.sql", "/createTableStudents.sql",
            "/createTableCourses.sql", "/createTableStudents_Courses.sql");

    public void execute() throws SQLException {
        CreateTables.create(executableScripts);
    }

}