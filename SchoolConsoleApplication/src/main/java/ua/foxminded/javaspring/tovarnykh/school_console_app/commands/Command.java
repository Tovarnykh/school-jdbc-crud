package ua.foxminded.javaspring.tovarnykh.school_console_app.commands;

public enum Command {
    
    INIT(0, new InitializeDatabase()), 
    POPULATE(100, new PopulateDatabase()), 
    FIND_GROUP(1, new FindGroup()),
    FIND_STUDENTS(2, new FindStudents()), 
    ADD_STUDENT(3, new AddStudent()), 
    DELETE_STUDENT(4, new DeleteStudent()),
    ADD_TO_COURSE(5, new AddToCourse()), 
    REMOVE_STUDENT(6, new RemoveStudent());
    
    private int index;
    private ControllerCommand command;
    
    Command(int index, ControllerCommand command) {
        this.index = index;
        this.command = command;
    }

    public int getIndex() {
        return index;
    }

    public ControllerCommand getCommand() {
        return command;
    }
    
}