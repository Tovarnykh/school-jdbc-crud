package ua.foxminded.javaspring.tovarnykh.school_console_app.commands;

import java.sql.SQLException;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.StudentsCoursesDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.fabric.FabricDao;
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
        
        try {
        int studentId = ConsolePrinter.readNumber();
        int groupId = ConsolePrinter.readNumber();
        StudentsCoursesDao studentsCoursesDAO = FabricDao.getStudentsCoursesDao();

        studentsCoursesDAO
        .delete(studentId, groupId);
        } catch (SQLException e) {
            System.out.println("Incorrect data in Input");
        }
        ConsolePrinter.closeSection();
    }

}