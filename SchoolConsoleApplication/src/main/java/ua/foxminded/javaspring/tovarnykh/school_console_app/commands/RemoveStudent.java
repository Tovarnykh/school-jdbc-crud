package ua.foxminded.javaspring.tovarnykh.school_console_app.commands;

import java.sql.SQLException;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.StudentsCoursesDAO;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.fabric.FabricDAO;
import ua.foxminded.javaspring.tovarnykh.school_console_app.main.ConsolePrinter;

/**
 * @author Victor Tovarnykh
 * @version 0.15.0
 * @since 0.1.0
 */
public class RemoveStudent implements ControllerCommand {

    @Override
    public void execute() throws SQLException {
        System.out.print("""
                ╔══════════════════════════════════════════╗
                ║      Insert student Id and course Id     ║
                ║     to remove student from the course.   ║
                ╟──────────────────────────────────────────╢
                 in:
                 """);
        int studentId = ConsolePrinter.readNumber();
        int groupId = ConsolePrinter.readNumber();
        StudentsCoursesDAO studentsCoursesDAO = FabricDAO.getStudentsCourses();

        studentsCoursesDAO.delete(studentId, groupId);
        ConsolePrinter.closeWindow();
    }

}