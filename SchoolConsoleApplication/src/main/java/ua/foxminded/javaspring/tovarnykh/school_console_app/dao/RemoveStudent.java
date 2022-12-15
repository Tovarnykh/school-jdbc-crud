package ua.foxminded.javaspring.tovarnykh.school_console_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.ConnectionAspect;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.commands.Command;
import ua.foxminded.javaspring.tovarnykh.school_console_app.main.ConsoleInterface;

public class RemoveStudent implements Command {

    private static final String QUERY = """
            DELETE FROM students_courses
            WHERE student_id = (?) AND course_id = (?)
            """;

    @Override
    public String execute() throws SQLException {
        try (Connection connection = ConnectionAspect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
                Statement statement = connection.createStatement()) {
            System.out.print(">");
            int studentId = Integer.parseInt(ConsoleInterface.in.nextLine());
            System.out.print(">");
            int groupId = Integer.parseInt(ConsoleInterface.in.nextLine());

            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, groupId);
            preparedStatement.executeUpdate();
        }
        return "true";
    }

}