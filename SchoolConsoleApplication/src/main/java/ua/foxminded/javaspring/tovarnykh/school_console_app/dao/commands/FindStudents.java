package ua.foxminded.javaspring.tovarnykh.school_console_app.dao.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.ConnectionAspect;
import ua.foxminded.javaspring.tovarnykh.school_console_app.main.ConsoleInterface;

/**
 *
 * @author Victor Tovarnykh
 * @version 0.15.0
 * @since 0.1.0
 */
public class FindStudents implements Command {

    private static final String QUERY = """
            SELECT CONCAT(first_name, ' ' , last_name) AS student
            FROM students_courses
            JOIN students ON students.student_id = students_courses.student_id
            JOIN courses ON courses.course_id = students_courses.course_id
            WHERE course_name = (?)
            ORDER BY first_name
            """;

    /**
     * Method name: execute
     *
     * @return (String) Received data from database.
     * @throws SQLException
     *
     *                      Inside the function: 1. Read user`s input. 2. Filling
     *                      gaps with user`s input. 3. Send query to database, 4.
     *                      Receive answer from database.
     */
    @Override
    public String execute() throws SQLException {
        StringBuilder resultList = new StringBuilder();

        try (Connection connection = ConnectionAspect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            String courseName = ConsoleInterface.readLine();

            preparedStatement.setString(1, courseName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultList.append(ConsoleInterface.SEPARATOR);
            resultList.append(String.format(" %25s %s %n", "Students of", courseName));
            resultList.append(ConsoleInterface.SEPARATOR);
            while (resultSet.next()) {
                resultList.append(String.format(" %28s%n", resultSet.getString("student")));
            }
            return resultList.toString();
        }
    }

}