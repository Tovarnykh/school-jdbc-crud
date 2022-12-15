package ua.foxminded.javaspring.tovarnykh.school_console_app.dao.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.ConnectionAspect;
import ua.foxminded.javaspring.tovarnykh.school_console_app.main.ConsoleInterface;

public class AddStudent implements Command{
    
    private static final String QUERY = """
            INSERT INTO students (group_id, first_name, last_name) VALUES (?, ?, ?)
            """;

    @Override
    public String execute() throws SQLException {
        try (Connection connection = ConnectionAspect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
                Statement statement = connection.createStatement()) {
            System.out.print(">");
            int groupId = Integer.parseInt(ConsoleInterface.in.nextLine());
            System.out.print(">");
            String firstName = ConsoleInterface.in.nextLine();
            System.out.print(">");
            String lastName = ConsoleInterface.in.nextLine();
            preparedStatement.setInt(1, groupId);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.executeUpdate();
            return "true";
        }
    }

}