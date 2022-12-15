package ua.foxminded.javaspring.tovarnykh.school_console_app.dao.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.ConnectionAspect;
import ua.foxminded.javaspring.tovarnykh.school_console_app.main.ConsoleInterface;

public class DeleteStudent implements Command {

    private static final String QUERY = """
            DELETE FROM students
            WHERE student_id = (?)
                        """;

    @Override
    public String execute() throws SQLException {
        try (Connection connection = ConnectionAspect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
                Statement statement = connection.createStatement()) {
            int studentId = Integer.parseInt(ConsoleInterface.in.nextLine());
            
            preparedStatement.setInt(1, studentId);
            preparedStatement.executeUpdate();
            return "true";
        }
    }

}