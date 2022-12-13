package ua.foxminded.javaspring.tovarnykh.school_console_app.dao;

import java.sql.SQLException;

interface Command {
    
    public void execute() throws SQLException;
 
}
