package ua.foxminded.javaspring.tovarnykh.school_console_app.dao.statements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.ConnectionAspect;

public class InsertMultiplyStudentsCourses {
    
    public static final String QUERY = "INSERT INTO students_courses(student_id, course_id) VALUES (?,?);";
    
    public static void insert(List<String> records) throws SQLException {
        try (Connection connection = ConnectionAspect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            records.stream()
            .map(simpleRecord -> simpleRecord.split(" "))
            .forEach(simpleRecord -> {
                try {
                    preparedStatement.setInt(1, Integer.parseInt(simpleRecord[0]));
                    preparedStatement.setInt(2, Integer.parseInt(simpleRecord[1]));
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
