package ua.foxminded.javaspring.tovarnykh.console_app.dao.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.CommandProvider;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.DatabaseProperties;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.ConnectionAspect;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.commands.AddStudent;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.commands.AddToCourse;

class AddToCourseTest {

    private static Connection conn;

    @BeforeAll
    static void setUp() {
        try {
            Class.forName("org.h2.Driver");
            DatabaseProperties.setDriver("jdbc:h2:mem:testdb");
            conn = DriverManager.getConnection(DatabaseProperties.getDriver(), DatabaseProperties.getUserName(),
                    DatabaseProperties.getPassword());
            CommandProvider.executeCommand(0);
            Connection connection = ConnectionAspect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(AddStudent.QUERY);

            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "Adam");
            preparedStatement.setString(3, "Adamson");
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        conn.close();
        DatabaseProperties.setDriver("jdbc:postgresql://localhost:5432/school");
    }

    @Test
    void execute_CheckIsStudentWasAddToCourse_True() throws Exception {
        Connection connection = ConnectionAspect.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(AddToCourse.QUERY);
        preparedStatement.setInt(1, 201);
        preparedStatement.setInt(2, 1);
        preparedStatement.executeUpdate();
        PreparedStatement checkStatement = connection
                .prepareStatement("SELECT * FROM students_courses WHERE student_id = 201 AND course_id = 1");
        ResultSet resultSet = checkStatement.executeQuery();
        assertTrue(resultSet.next());
    }

}
