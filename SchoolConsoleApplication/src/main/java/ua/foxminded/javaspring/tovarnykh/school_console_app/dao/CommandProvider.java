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

public class CommandProvider {

    private static List<Command> commands = List.of(new InitializeDatabase(), new FindGroup(), new FindStudents(),
            new AddStudent(), new DeleteStudent(), new AddToCourse(), new RemoveStudent());

    public static String executeCommand(int commandNumber) throws SQLException {
        return commands.get(commandNumber).execute();
    }

    private CommandProvider() {

    }

}