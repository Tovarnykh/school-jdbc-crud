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

    @Override
    public String execute() throws SQLException {
        try (Connection connection = ConnectionAspect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
                Statement statement = connection.createStatement()) {
            System.out.print(">");
            int studentId = Integer.parseInt(ConsoleInterface.in.nextLine());
            System.out.print(">");
            int courseId = Integer.parseInt(ConsoleInterface.in.nextLine());
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);
            preparedStatement.executeUpdate();
            return "true";
        }
    }

}