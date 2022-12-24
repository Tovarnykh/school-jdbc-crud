package ua.foxminded.javaspring.tovarnykh.school_console_app.dao.statements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.ConnectionAspect;

/**
 *
 * @author Victor Tovarnykh
 * @version 0.15.0
 * @since 0.1.0
 */
public class SelectStudent {

    public static final String QUERY = """
            SELECT CONCAT(first_name, ' ' , last_name) AS student
            FROM students_courses
            JOIN students ON students.student_id = students_courses.student_id
            JOIN courses ON courses.course_id = students_courses.course_id
            WHERE course_name = (?)
            ORDER BY first_name
            """;

    public static CachedRowSet select(String courseName) throws SQLException {
        CachedRowSet rowSet = RowSetProvider.newFactory().createCachedRowSet();
        try (Connection connection = ConnectionAspect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setString(1, courseName);
            ResultSet resultSet =  preparedStatement.executeQuery();
            rowSet.populate(resultSet);
            return rowSet;
        }
    }

}