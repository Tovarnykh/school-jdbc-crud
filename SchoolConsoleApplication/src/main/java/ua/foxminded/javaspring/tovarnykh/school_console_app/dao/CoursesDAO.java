package ua.foxminded.javaspring.tovarnykh.school_console_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.config.ConnectionManager;

public class CoursesDAO {

    private static final String INSERT_COURSES = "INSERT INTO courses (course_name) VALUES (?);";

    public void insertMultiplyCourses(List<String> courses) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COURSES)) {
            for (String course : courses) {
                preparedStatement.setString(1, course);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }

}
