package ua.foxminded.javaspring.tovarnykh.school_console_app.commands;

import java.sql.SQLException;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.statements.DeleteStudentById;
import ua.foxminded.javaspring.tovarnykh.school_console_app.main.ConsolePrinter;

/**
 *
 * @author Victor Tovarnykh
 * @version 0.15.0
 * @since 0.1.0
 */
public class DeleteStudent implements Command {

    @Override
    public void execute() throws SQLException {
        System.out.print("""
                ╔══════════════════════════════════════════╗
                ║        Insert student Id to delete       ║
                ╟──────────────────────────────────────────╢
                 in:
                 """);
        int studentId = ConsolePrinter.readNumber();

        DeleteStudentById.delete(studentId);
        ConsolePrinter.closeWindow();
    }

}