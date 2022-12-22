package ua.foxminded.javaspring.tovarnykh.school_console_app.dao;

import java.sql.SQLException;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.commands.Commands;

/**
 * @author Victor Tovarnykh
 * @version 0.15.0
 * @since 0.1.0
 */
public class CommandProvider {

    /**
     * Method name: executeCommand
     * 
     * @param commandNumber Number of command that needs to be to executed.
     * @return (String) Received driver.
     *
     *         Execute command by given number.
     */
    public static void executeCommand(Commands command) throws SQLException {
        command.getCommand().execute();
    }

    private CommandProvider() {

    }

}