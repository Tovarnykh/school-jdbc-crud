package ua.foxminded.javaspring.tovarnykh.school_console_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.config.ConnectionManager;

public class GroupsDAO {

    private static final String INSERT_GROUPS = "INSERT INTO groups (group_name) VALUES (?);";

    private static final String SELECT_GROUPS = """
            SELECT group_name,  COUNT(student_id) AS inscribed_students
            FROM students
            JOIN groups ON groups.group_id = students.group_id
            GROUP BY group_name
            HAVING COUNT(student_id) >= (?)
            ORDER BY inscribed_students
            """;

    public void insertMultiply(List<String> groups) {
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT_GROUPS)) {
            for (String group : groups) {
                statement.setString(1, group);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CachedRowSet select(int amountOfStudents) throws SQLException {
        CachedRowSet rowSet = RowSetProvider.newFactory().createCachedRowSet();
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GROUPS)) {
            preparedStatement.setInt(1, amountOfStudents);
            ResultSet resultSet = preparedStatement.executeQuery();
            rowSet.populate(resultSet);
            return rowSet;
        }
    }

}
