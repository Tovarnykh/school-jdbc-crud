package ua.foxminded.javaspring.tovarnykh.school_console_app.commands;

import java.sql.SQLException;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.StudentsDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.fabric.FabricDao;
import ua.foxminded.javaspring.tovarnykh.school_console_app.main.ConsolePrinter;

/**
 *
 * @author Victor Tovarnykh
 * @version 0.15.0
 * @since 0.1.0
 */
public class AddStudent implements ControllerCommand {

    @Override
    public void execute() {
        System.out.print("""
                ╔══════════════════════════════════════════╗
                ║Insert: > group id,                       ║
                ║        > first name,                     ║
                ║        > last_name                       ║
                ║        to add student.                   ║
                ╟──────────────────────────────────────────╢
                 in:
                 """);

        try {
            int groupId = ConsolePrinter.readNumber();
            String firstName = ConsolePrinter.readLine();
            String lastName = ConsolePrinter.readLine();
            
            StudentsDao studentDAO = FabricDao.getStudentsDao();
            studentDAO.insert(groupId, firstName, lastName);
        } catch (NumberFormatException|SQLException e) {
            System.out.println("Incorrect data in Input");
        }
        ConsolePrinter.closeSection();
    }

}