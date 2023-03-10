package ua.foxminded.javaspring.tovarnykh.school_console_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import ua.foxminded.javaspring.tovarnykh.school_console_app.commands.PopulateDatabase;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.config.ConnectionManager;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.pojo.Student;

public class StudentsDao {

    private static final String DELETE_STUDENTS = """
            DELETE FROM students
            WHERE student_id = (?)
            """;
    private static final String INSERT_STUDENTS = """
            INSERT INTO students (group_id, first_name, last_name) VALUES (?, ?, ?)
            """;

    public void insert(int groupId, String firstName, String lastName) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement(INSERT_STUDENTS)) {
            preparedStatement.setInt(1, groupId);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.executeUpdate();
        }
    }

    public void insertMultiply(List<Student> list) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement(INSERT_STUDENTS)) {
            list.stream().forEach(student -> {
                try {
                    if (student.getGroupId() > PopulateDatabase.groups.size()) {
                        preparedStatement.setNull(1, java.sql.Types.INTEGER);
                    } else {
                        preparedStatement.setInt(1, student.getGroupId());
                    }
                    preparedStatement.setString(2, student.getFirstName());
                    preparedStatement.setString(3, student.getLastName());
                    preparedStatement.addBatch();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            preparedStatement.executeBatch();
        }
    }

    public void delete(int studentId) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement(DELETE_STUDENTS)) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.executeUpdate();
        }
    }

}