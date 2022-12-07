package ua.foxminded.javaspring.tovarnykh.school_console_app.dao;

import java.sql.SQLException;

abstract class Command {
    
    protected static final String JDBC_DRIVER = "jdbc:postgresql://localhost:5432/school";
    protected static final String USER_NAME = "guest";
    protected static final String PASSWORD = "foxminded";
    
    abstract public void execute() throws SQLException;

}
