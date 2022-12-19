package ua.foxminded.javaspring.tovarnykh.school_console_app.dao;

import java.sql.SQLException;
import java.util.List;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.commands.AddStudent;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.commands.AddToCourse;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.commands.Command;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.commands.DeleteStudent;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.commands.FindGroup;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.commands.FindStudents;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.commands.InitializeDatabase;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.commands.RemoveStudent;

/**
 * @author Victor Tovarnykh
 * @version 0.15.0
 * @since 0.1.0
 */
public class CommandProvider {

    private static List<Command> commands = List.of(new InitializeDatabase(), new FindGroup(), new FindStudents(),
            new AddStudent(), new DeleteStudent(), new AddToCourse(), new RemoveStudent());

    /**
     * Method name: executeCommand
     * 
     * @param commandNumber Number of command that needs to be to executed.
     * @return (String) Received driver.
     *
     *         Execute command by given number.
     */
    public static String executeCommand(int commandNumber) throws SQLException {
        return commands.get(commandNumber).execute();
    }

    private CommandProvider() {

    }

}