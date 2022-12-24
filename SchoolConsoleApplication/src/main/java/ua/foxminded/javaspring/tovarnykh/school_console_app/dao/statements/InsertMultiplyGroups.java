package ua.foxminded.javaspring.tovarnykh.school_console_app.dao.statements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.ConnectionAspect;

public class InsertMultiplyGroups {

    public static final String QUERY = "INSERT INTO groups (group_name) VALUES (?);";

    private InsertMultiplyGroups() {
        
    }
    
    public static void insert(List<String> groups) {
        try (Connection connection = ConnectionAspect.getConnection();
                PreparedStatement statement = connection.prepareStatement(QUERY)) {
            for (String group : groups) {
                statement.setString(1, group);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}