package ua.foxminded.javaspring.tovarnykh.school_console_app.dao.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.ConnectionAspect;
import ua.foxminded.javaspring.tovarnykh.school_console_app.main.ConsoleInterface;

public class AddToCourse implements Command {

    private static final String QUERY = """
            INSERT INTO students_courses(student_id, course_id) VALUES (?,?)
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
            int courseId = ConsoleInterface.readNumber();

            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);
            preparedStatement.executeUpdate();
            return "true";
        }
    }

}