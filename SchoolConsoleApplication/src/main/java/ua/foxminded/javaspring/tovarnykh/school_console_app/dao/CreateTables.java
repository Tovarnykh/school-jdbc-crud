package ua.foxminded.javaspring.tovarnykh.school_console_app.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.stream.Stream;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateTables extends Command {

    private static final String GROUPS_SCRIPT = """
             DROP TABLE IF EXISTS groups CASCADE;
             CREATE TABLE groups(
                group_id SERIAL PRIMARY KEY,
                group_name character varying(5) NOT NULL
            );""";

    private static final String STUDENTS_SCRIPT = """
             DROP TABLE IF EXISTS students;
             CREATE TABLE students(
                student_id SERIAL PRIMARY KEY,
                group_id integer REFERENCES groups,
                first_name character varying(20) NOT NULL,
                last_name character varying(25) NOT NULL
            );""";

    private static final String COURSES_SCRIPT = """
             DROP TABLE IF EXISTS courses;
             CREATE TABLE courses(
                course_id SERIAL PRIMARY KEY,
                course_name character varying(20) NOT NULL,
                course_description TEXT
            );""";

    @Override
    public void execute() throws SQLException {
        Stream.of(GROUPS_SCRIPT, STUDENTS_SCRIPT, COURSES_SCRIPT).forEach(CreateTables::create);

    }

    private static void create(String script) {
        try (Connection connection = DriverManager.getConnection(JDBC_DRIVER, USER_NAME, PASSWORD);
                Statement statement = connection.createStatement();) {
            statement.executeUpdate(script);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
