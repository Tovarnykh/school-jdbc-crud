package ua.foxminded.javaspring.tovarnykh.school_console_app.dao.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.ConnectionAspect;
import ua.foxminded.javaspring.tovarnykh.school_console_app.main.ConsoleInterface;

/**
*
* @author Victor Tovarnykh
* @version 0.15.0
* @since 0.1.0
*/
public class DeleteStudent implements Command {

    private static final String QUERY = """
            DELETE FROM students
            WHERE student_id = (?)
                        """;
    
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
    @Override
    public String execute() throws SQLException {
        try (Connection connection = ConnectionAspect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
                Statement statement = connection.createStatement()) {
            int studentId = ConsoleInterface.readNumber();

            preparedStatement.setInt(1, studentId);
            preparedStatement.executeUpdate();
            return "true";
        }
    }

}