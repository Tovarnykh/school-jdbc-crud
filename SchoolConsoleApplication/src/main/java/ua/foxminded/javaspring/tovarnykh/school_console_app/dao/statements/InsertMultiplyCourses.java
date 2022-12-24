package ua.foxminded.javaspring.tovarnykh.school_console_app.dao.statements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.ConnectionAspect;

public class InsertMultiplyCourses {

    private static final String QUERRY = "INSERT INTO courses (course_name) VALUES (?);";
    
    private InsertMultiplyCourses() {
        
    }

    public static void insert(List<String> courses) throws SQLException {
        try (Connection connection = ConnectionAspect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(QUERRY)) {
            for (String course : courses) {
                preparedStatement.setString(1, course);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }

}