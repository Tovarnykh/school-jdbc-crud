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
public class FindGroup implements Command {

    public static final String QUERY = """
            SELECT course_name,  COUNT(student_id) AS inscribed_students
            FROM students_courses
            JOIN courses ON courses.course_id = students_courses.course_id
            GROUP BY course_name
            HAVING COUNT(student_id) >= (?)
            ORDER BY course_name
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
    public void execute() throws SQLException {
        try (Connection connection = ConnectionAspect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            int amountOfStudents = ConsoleInterface.readNumber();
            StringBuilder resultList = new StringBuilder();

            preparedStatement.setInt(1, amountOfStudents);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultList.append(ConsoleInterface.SEPARATOR);
            resultList.append(String.format(" %-20s | %s %n", "Course name", "Inscribed students"));
            resultList.append(ConsoleInterface.SEPARATOR);
            while (resultSet.next()) {
                resultList.append(String.format(" %-20s | %11d %n", resultSet.getString("course_name"),
                        resultSet.getInt("inscribed_students")));
            }
            System.out.println(resultList.toString());
        }
    }
}