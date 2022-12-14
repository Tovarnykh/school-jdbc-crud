package ua.foxminded.javaspring.tovarnykh.school_console_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.ConnectionAspect;

public class FindGroup implements Command {

    private static final String FIND_GROUPS_QUERY = """
            SELECT course_name,  COUNT(student_id) AS inscribed_students
            FROM students_courses
            JOIN courses ON courses.course_id = students_courses.course_id
            GROUP BY course_name
            HAVING COUNT(student_id) >= (?)
            ORDER BY course_name
            """;
    
    private static final String SEPARATOR = "-----------------------------------------";

    @Override
    public void execute() throws SQLException {
        try (Connection connection = ConnectionAspect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_GROUPS_QUERY);
                Statement statement = connection.createStatement()) {
            preparedStatement.setInt(1, DatabaseProperties.querryNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(SEPARATOR);
            System.out.printf("%-20s | %s \n","Course name", "Inscribed students");
            System.out.println(SEPARATOR);
            while (resultSet.next()) {
                System.out.printf("%-20s | %11d \n", resultSet.getString("course_name"),
                        resultSet.getInt("inscribed_students"));
            }
        }
    }

}
