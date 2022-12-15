package ua.foxminded.javaspring.tovarnykh.school_console_app.dao.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.DatabaseProperties;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.ConnectionAspect;
import ua.foxminded.javaspring.tovarnykh.school_console_app.main.ConsoleInterface;

public class FindGroup implements Command {

    private static final String QUERY = """
            SELECT course_name,  COUNT(student_id) AS inscribed_students
            FROM students_courses
            JOIN courses ON courses.course_id = students_courses.course_id
            GROUP BY course_name
            HAVING COUNT(student_id) >= (?)
            ORDER BY course_name
            """;

    @Override
    public String execute() throws SQLException {
        StringBuilder resultList = new StringBuilder();

        try (Connection connection = ConnectionAspect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
                Statement statement = connection.createStatement()) {
            int amountOfStudents = Integer.parseInt(ConsoleInterface.in.nextLine());

            preparedStatement.setInt(1, amountOfStudents);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultList.append(DatabaseProperties.SEPARATOR + "\n");
            resultList.append(String.format("%-20s | %s \n", "Course name", "Inscribed students"));
            resultList.append(DatabaseProperties.SEPARATOR + "\n");
            while (resultSet.next()) {
                resultList.append(String.format("%-20s | %11d \n", resultSet.getString("course_name"),
                        resultSet.getInt("inscribed_students")));
            }
            return resultList.toString();
        }
    }
}