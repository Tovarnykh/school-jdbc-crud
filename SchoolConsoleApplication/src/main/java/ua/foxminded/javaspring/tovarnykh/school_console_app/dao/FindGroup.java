package ua.foxminded.javaspring.tovarnykh.school_console_app.dao;

import java.sql.SQLException;

public class FindGroup implements Command {
    
    private static final String FIND_GROUPS_QUERY = "INSERT INTO groups (group_name) VALUES (?)";

    @Override
    public void execute() throws SQLException {
        // TODO Auto-generated method stub
        
    }

}
