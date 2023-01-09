package ua.foxminded.javaspring.tovarnykh.school_console_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.config.ConnectionManager;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.pojo.Student;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.pojo.StudentsCourses;

public class StudentsCoursesDao {

    private static final String INSERT_STUDENTS_COURSES = "INSERT INTO students_courses(student_id, course_id) VALUES (?,?);";

    private static final String DELETE_STUDENTS_COURSES = """
            DELETE FROM students_courses
            WHERE student_id = (?) AND course_id = (?)
            """;

    private static final String SELECT_STUDENTSCOURSES = """
            SELECT first_name, last_name
            FROM students_courses sc
            JOIN students s ON s.student_id = sc.student_id
            JOIN courses c ON c.course_id = sc.course_id
            WHERE course_name = (?)
            ORDER BY first_name
            """;

    public void insert(int studentId, int courseId) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENTS_COURSES)) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);
            preparedStatement.executeUpdate();
        }
    }

    public void insertMultiply(List<StudentsCourses> list) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENTS_COURSES)) {
            list.stream().forEach(studentsCourses -> {
                try {
                    preparedStatement.setInt(1, studentsCourses.getStudentId());
                    preparedStatement.setInt(2, studentsCourses.getCourseID());
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

    public void delete(int studentId, int groupId) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENTS_COURSES)) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, groupId);
            preparedStatement.executeUpdate();
        }
    }

    public List<Student> getStudentsInCourse(String courseName) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENTSCOURSES)) {
            preparedStatement.setString(1, courseName);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Student> students = new ArrayList<>();
            
            while (resultSet.next()) {
                students.add(new Student(resultSet.getString("first_name"), resultSet.getString("last_name")));
            }
            return students;
        }
    }

}
