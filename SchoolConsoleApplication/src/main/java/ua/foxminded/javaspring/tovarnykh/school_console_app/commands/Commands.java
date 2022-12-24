package ua.foxminded.javaspring.tovarnykh.school_console_app.commands;


public enum Commands {
    INIT(0, new InitializeDatabase()), GENERATE(100, new GenerateData()), FIND_GROUP(1, new FindGroup()),
    FIND_STUDENTS(2, new FindStudents()), ADD_STUDENT(3, new AddStudent()), DELETE_STUDENT(4, new DeleteStudent()),
    ADD_TO_COURSE(5, new AddToCourse()), REMOVE_STUDENT(6, new RemoveStudent());

    private int index;
    private Command command;

    public int getIndex() {
        return index;
    }

    public Command getCommand() {
        return command;
    }

    Commands(int index, Command command) {
        this.index = index;
        this.command = command;
    }
}
