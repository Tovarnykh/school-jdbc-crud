package ua.foxminded.javaspring.tovarnykh.console_app.dao.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

class CoursesDaoTest extends DatabaseTest {

    @Test
    void insertMultiply_CheckIfTenCoursesWhereGenerated_True() throws Exception {
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement
                        .executeQuery("SELECT COUNT(course_id) as courses_count FROM courses")) {
            while (resultSet.next()) {
                assertEquals(10, resultSet.getInt("courses_count"));
            }
        }
    }

}
