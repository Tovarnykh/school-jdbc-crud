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
public class SelectGroup {

    public static final String QUERY = """
            SELECT group_name,  COUNT(student_id) AS inscribed_students
            FROM students
            JOIN groups ON groups.group_id = students.group_id
            GROUP BY group_name
            HAVING COUNT(student_id) >= (?)
            ORDER BY inscribed_students
            """;

    public static CachedRowSet select(int amountOfStudents) throws SQLException {
        CachedRowSet rowSet = RowSetProvider.newFactory().createCachedRowSet();
        try (Connection connection = ConnectionAspect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setInt(1, amountOfStudents);
            ResultSet resultSet = preparedStatement.executeQuery();
            rowSet.populate(resultSet);
            return rowSet;
        }
    }
}