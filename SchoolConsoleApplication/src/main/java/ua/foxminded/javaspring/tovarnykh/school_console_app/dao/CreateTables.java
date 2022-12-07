package ua.foxminded.javaspring.tovarnykh.school_console_app.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.stream.Stream;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateTables extends Command {

    private static final String groupsScript = """
             CREATE TABLE groups(
                group_id SERIAL PRIMARY KEY,
                group_name character varying(30) NOT NULL
            );""";

    private static final String studentsScript = """
             CREATE TABLE students(
                student_id SERIAL PRIMARY KEY,
                group_id integer NOT NULL REFERENCES groups,
                first_name character varying(20) NOT NULL,
                last_name character varying(25) NOT NULL
            );""";

    private static final String coursesScript = """
             CREATE TABLE courses(
                course_id SERIAL PRIMARY KEY,
                course_name character varying(20) NOT NULL,
                course_description TEXT
            );""";

    @Override
    void execute() throws SQLException {
        Stream.of(groupsScript, studentsScript, coursesScript).forEach(CreateTables::create);

    }

    private static void create(String script) {
        try (Connection connection = DriverManager.getConnection(JDBC_DRIVER, USER_NAME, PASSWORD);
                Statement statement = connection.createStatement();) {
            statement.executeQuery(script);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
