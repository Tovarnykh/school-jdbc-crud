package ua.foxminded.javaspring.tovarnykh.school_console_app.dao.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.DatabaseProperties;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.ConnectionAspect;
import ua.foxminded.javaspring.tovarnykh.school_console_app.main.ConsoleInterface;

public class FindStudents implements Command {

    private static final String QUERY = """
            SELECT CONCAT(first_name, ' ' , last_name) AS student
            FROM students_courses
            JOIN students ON students.student_id = students_courses.student_id
            JOIN courses ON courses.course_id = students_courses.course_id
            WHERE course_name = (?)
            ORDER BY first_name
            """;

    @Override
    public String execute() throws SQLException {
        StringBuilder resultList = new StringBuilder();

        try (Connection connection = ConnectionAspect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
                Statement statement = connection.createStatement()) {
            String courseName = ConsoleInterface.in.nextLine();
            preparedStatement.setString(1, courseName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultList.append(" " + DatabaseProperties.SEPARATOR + "\n");
            resultList.append(String.format("%25s %s \n", "Students of", courseName));
            resultList.append(" " + DatabaseProperties.SEPARATOR + "\n");
            while (resultSet.next()) {
                resultList.append(String.format("%28s\n", resultSet.getString("student")));
            }
            return resultList.toString();
        }
    }

}