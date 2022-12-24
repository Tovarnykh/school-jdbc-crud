package ua.foxminded.javaspring.tovarnykh.school_console_app.dao.statements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.ConnectionAspect;

public class InsertMultiplyStudent {

    public static final String QUERY = """
            INSERT INTO students (group_id, first_name, last_name) VALUES (?, ?, ?)
            """;
    
    public static void insert(List<String> students) throws SQLException {
        try (Connection connection = ConnectionAspect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            students.stream()
            .map(student -> student.split(" "))
            .forEach(student -> {
                try {
                    preparedStatement.setInt(1, Integer.parseInt(student[0]));
                    preparedStatement.setString(2, student[1]);
                    preparedStatement.setString(3, student[2]);
                    preparedStatement.addBatch();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            preparedStatement.executeBatch();
        }
    }
    
}
