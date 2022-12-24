package ua.foxminded.javaspring.tovarnykh.school_console_app.commands;

import java.sql.SQLException;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.statements.InsertStudent;
import ua.foxminded.javaspring.tovarnykh.school_console_app.main.ConsolePrinter;

/**
 *
 * @author Victor Tovarnykh
 * @version 0.15.0
 * @since 0.1.0
 */
public class AddStudent implements Command {

    @Override
    public void execute() throws SQLException {
            System.out.print("""
                    ╔══════════════════════════════════════════╗
                    ║Insert: group id, first name and last_name║
                    ║              to add student.             ║
                    ╟──────────────────────────────────────────╢
                     in:
                     """);

            int groupId = ConsolePrinter.readNumber();
            String firstName = ConsolePrinter.readLine();
            String lastName = ConsolePrinter.readLine();

            InsertStudent.insert(groupId, firstName, lastName);
            ConsolePrinter.closeWindow();
    }

}