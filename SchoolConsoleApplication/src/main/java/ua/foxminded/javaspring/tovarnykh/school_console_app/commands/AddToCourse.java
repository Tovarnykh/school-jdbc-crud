package ua.foxminded.javaspring.tovarnykh.school_console_app.commands;

import java.sql.SQLException;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.StudentsCoursesDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.fabric.FabricDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.main.ConsolePrinter;

public class AddToCourse implements ControllerCommand {

    @Override
    public void execute() {
        System.out.print("""
                ╔══════════════════════════════════════════╗
                ║      Insert student Id and course Id     ║
                ║      to enroll student to the course.    ║
                ╟──────────────────────────────────────────╢
                 in:
                 """);
        int studentId = ConsolePrinter.readNumber();
        int courseId = ConsolePrinter.readNumber();

        try {
            StudentsCoursesDao studentsCoursesDAO = FabricDao.getStudentsCoursesDao();
            studentsCoursesDAO.insert(studentId, courseId);
        } catch (SQLException e) {
            System.out.println("Incorrect data in Input");
        }
        ConsolePrinter.closeSection();
    }

}