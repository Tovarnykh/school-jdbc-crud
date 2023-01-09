package ua.foxminded.javaspring.tovarnykh.school_console_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.config.ConnectionManager;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.pojo.Group;

public class GroupsDao {

    private static final String INSERT_GROUPS = "INSERT INTO groups (group_name) VALUES (?);";

    private static final String SELECT_GROUPS = """
            SELECT g.group_name,  COUNT(s.student_id) AS inscribed_students
            FROM students s
            JOIN groups g ON g.group_id = s.group_id
            GROUP BY g.group_name
            HAVING COUNT(student_id) <= (?)
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

    public List<Group> getStudentsAmountInGroups(int amountOfStudents) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GROUPS)) {
            preparedStatement.setInt(1, amountOfStudents);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Group> groups = new ArrayList<>();
            
            while (resultSet.next()) {
                groups.add(new Group(resultSet.getString("group_name"), resultSet.getInt("inscribed_students")));
            }
            return groups;
        }
    }

}
