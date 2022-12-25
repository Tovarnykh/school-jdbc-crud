package ua.foxminded.javaspring.tovarnykh.school_console_app.commands;

import java.sql.SQLException;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.statements.InsertStudentsCourses;
import ua.foxminded.javaspring.tovarnykh.school_console_app.main.ConsolePrinter;

public class AddToCourse implements Command {

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
            InsertStudentsCourses.insert(studentId, courseId);
        } catch (SQLException e) {
            System.out.println("Incorrect data in Input");
        }
        ConsolePrinter.closeWindow();
    }

}