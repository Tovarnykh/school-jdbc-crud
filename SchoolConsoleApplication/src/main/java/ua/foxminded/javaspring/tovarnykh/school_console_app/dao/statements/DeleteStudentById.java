package ua.foxminded.javaspring.tovarnykh.school_console_app.dao.statements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.ConnectionAspect;

/**
 *
 * @author Victor Tovarnykh
 * @version 0.15.0
 * @since 0.1.0
 */
public class DeleteStudentById{

    public static final String QUERY = """
            DELETE FROM students
            WHERE student_id = (?)
                        """;

    public static void delete(int studentId) throws SQLException {
        try (Connection connection = ConnectionAspect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.executeUpdate();
        }
    }

}