package ua.foxminded.javaspring.tovarnykh.school_console_app.dao.commands;

public enum Commands {
    INIT("0", new InitializeDatabase()), GENERATE("0.5", new GenerateData()), FINDGROUP("1", new FindGroup()),
    FINDSTUDENTS("2", new FindStudents()), ADDSTUDENT("3", new AddStudent()), DELETESTUDENT("4", new DeleteStudent()),
    ADDTOCOURSE("5", new AddToCourse()), REMOVESTUDENT("6", new RemoveStudent());

    private String index;
    private Command command;

    public String getIndex() {
        return index;
    }

    public Command getCommand() {
        return command;
    }

    Commands(String index, Command command) {
        this.index = index;
        this.command = command;
    }
}
