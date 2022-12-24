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

import ua.foxminded.javaspring.tovarnykh.school_console_app.commands.CommandProvider;
import ua.foxminded.javaspring.tovarnykh.school_console_app.commands.Commands;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.ConnectionAspect;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.DatabaseProperties;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.statements.InsertStudent;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.statements.InsertStudentsCourses;

class AddToCourseTest {

    private static Connection conn;

    @BeforeAll
    static void setUp() {
        try {
            Class.forName("org.h2.Driver");
            DatabaseProperties.readPropertyFile("testDatabaseProperties.properties");
            conn = DriverManager.getConnection(DatabaseProperties.getDriver(), DatabaseProperties.getUserName(),
                    DatabaseProperties.getPassword());
            CommandProvider.executeCommand(Commands.INIT);
            CommandProvider.executeCommand(Commands.GENERATE);
            Connection connection = ConnectionAspect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(InsertStudent.QUERY);

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
    }

    @Test
    void execute_CheckIsStudentWasAddToCourse_True() throws Exception {
        Connection connection = ConnectionAspect.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(InsertStudentsCourses.QUERY);
        preparedStatement.setInt(1, 201);
        preparedStatement.setInt(2, 1);
        preparedStatement.executeUpdate();
        PreparedStatement checkStatement = connection
                .prepareStatement("SELECT * FROM students_courses WHERE student_id = 201 AND course_id = 1");
        ResultSet resultSet = checkStatement.executeQuery();
        assertTrue(resultSet.next());
    }

}
