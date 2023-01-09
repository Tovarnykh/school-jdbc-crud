package ua.foxminded.javaspring.tovarnykh.console_app.dao.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.junit.jupiter.api.Test;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.StudentsCoursesDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.StudentsDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.fabric.FabricDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.pojo.Student;

class StudentsCoursesDaoTest extends DatabaseTest {

    @Test
    void insert_CheckIsStudentWasAddToCourse_True() throws Exception {
        StudentsDao studentDAO = FabricDao.getStudentsDao();
        StudentsCoursesDao studentsCoursesDAO = new StudentsCoursesDao();

        studentDAO.insert(1, "Adam", "Adamson");
        studentsCoursesDAO.insert(201, 1);

        try (PreparedStatement checkStatement = connection
                .prepareStatement("SELECT * FROM students_courses WHERE student_id = 201 AND course_id = 1");
                ResultSet resultSet = checkStatement.executeQuery()) {
            assertTrue(resultSet.next());
        }
    }
    
    @Test
    void delete_CheckIsStudentWasRemovedFromCourse_False() throws Exception {
        StudentsDao studentDAO = FabricDao.getStudentsDao();
        StudentsCoursesDao studentsCoursesDAO = new StudentsCoursesDao();

        studentDAO.insert(1, "Adam", "Adamson");
        studentsCoursesDAO.insert(201, 1);
        studentsCoursesDAO.delete(201, 1);
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement
                        .executeQuery("SELECT * FROM students_courses WHERE student_id = 201 AND course_id = 1")) {
            assertFalse(resultSet.next());
        }
    }
    
    @Test
    void getStudentsInCourse_CheckIfAnyStudentsWereFound_True() throws Exception {
        StudentsCoursesDao studentsCoursesDAO = new StudentsCoursesDao();
        List<Student> students = studentsCoursesDAO.getStudentsInCourse("Art");
        assertTrue(!students.isEmpty());
    }
    
}
