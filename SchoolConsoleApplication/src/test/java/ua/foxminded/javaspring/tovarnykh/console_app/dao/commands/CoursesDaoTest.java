package ua.foxminded.javaspring.tovarnykh.console_app.dao.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

class CoursesDaoTest extends DatabaseTest {

    private final String SQL_SCRIPT = "SELECT COUNT(course_id) as courses_count FROM courses";

    @Test
    void insertMultiply_CheckIfTenCoursesWhereGenerated_True() throws Exception {
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_SCRIPT)) {
            int coursesCount = 0;

            while (resultSet.next()) {
                coursesCount = resultSet.getInt("courses_count");
            }
            assertEquals(10, coursesCount);
        }
    }

}
