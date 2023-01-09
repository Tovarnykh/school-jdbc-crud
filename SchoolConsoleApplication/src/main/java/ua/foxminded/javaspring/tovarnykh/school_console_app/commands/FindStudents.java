package ua.foxminded.javaspring.tovarnykh.school_console_app.commands;

import java.sql.SQLException;
import java.util.List;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.StudentsCoursesDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.fabric.FabricDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.pojo.Student;
import ua.foxminded.javaspring.tovarnykh.school_console_app.main.ConsolePrinter;

/**
 *
 * @author Victor Tovarnykh
 * @version 0.15.0
 * @since 0.1.0
 */
public class FindStudents implements ControllerCommand {

    @Override
    public void execute() throws SQLException {
        System.out.print("""
                ╔══════════════════════════════════════════╗
                ║Insert a course name to find it`s students║
                ╟──────────────────────────────────────────╢
                 """);
        PopulateDatabase.COURSES.forEach(course -> System.out.printf(" %s \n", course));
        System.out.println(ConsolePrinter.SEPARATOR + " in:");

        String courseName = ConsolePrinter.readLine();
        StringBuilder resultList = new StringBuilder();
        StudentsCoursesDao studentsCoursesDAO = FabricDao.getStudentsCoursesDao();
        List<Student> students = studentsCoursesDAO.getStudentsInCourse(courseName);

        resultList.append(ConsolePrinter.SEPARATOR);
        resultList.append(String.format(" %25s %s %n", "Students of", courseName));
        resultList.append(ConsolePrinter.SEPARATOR);

        students.forEach(
                student -> resultList.append(String.format(" %s%s%n", student.getFirstName(), student.getLastName())));

        System.out.println(resultList.toString());
        
        ConsolePrinter.closeSection();
    }

}