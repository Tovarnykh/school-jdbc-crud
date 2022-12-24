package ua.foxminded.javaspring.tovarnykh.school_console_app.commands;

import java.sql.SQLException;

public interface Command {
    
    public void execute() throws SQLException;
 
}