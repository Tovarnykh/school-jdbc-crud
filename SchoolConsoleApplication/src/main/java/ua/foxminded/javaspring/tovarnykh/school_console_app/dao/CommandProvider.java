package ua.foxminded.javaspring.tovarnykh.school_console_app.dao;

import java.sql.SQLException;
import java.util.List;

public class CommandProvider {

    private static List<Command> commands = List.of(new InitializeDatabase());

    public static void executeCommand(int commandNumber) throws SQLException {
        commands.get(commandNumber).execute();
    }

    private CommandProvider() {

    }

}
