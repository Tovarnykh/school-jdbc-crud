package ua.foxminded.javaspring.tovarnykh.school_console_app.main;

import java.sql.SQLException;
import java.util.Scanner;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.CommandProvider;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.DatabaseProperties;

public class ConsoleInterface {

    private static Scanner in;
    private static final String EXIT = "exit";

    public ConsoleInterface() throws SQLException {
        in = new Scanner(System.in);
        CommandProvider.executeCommand(0);
    }

    public void exerciseChooser() throws SQLException {
        System.out.print("""
                ╔═════════════════════════════════════════╗
                ║ Welcome To School Console Application!  ║
                ║   Choose command To Interact with DB    ║
                ╠─────────────────────────────────────────╣
                ║1 - Find all groups with                 ║
                ║    less or equal students’ number.      ║
                ║2 - Find all students related to         ║
                ║    the course with the given name.      ║
                ║3 - Add a new student.                   ║
                ║4 - Delete a student.                    ║
                ║5 - Add a list of students to the course.║
                ║6 - Remove the student                   ║
                ║    from one of their courses.           ║
                ║                                         ║
                ║exit - to Exit                           ║
                ╚═════════════════════════════════════════╝
                 >""");

        String choice = in.nextLine();
        if (choice.equals("1")) {
            drawFindGroupsInterface();
        }

    }

    public void drawFindGroupsInterface() throws SQLException {
        System.out.print("""
                ╔════════════════════════════════════════╗
                ║Insert a students` number to find groups║
                ╟────────────────────────────────────────╢
                 in:""");
        DatabaseProperties.querryNumber = in.nextInt();
        CommandProvider.executeCommand(1);
        System.out.print("""

                ╚════════════════════════════════════════╝
                """);
    }

    @Override
    protected void finalize() {
        in.close();
    }

}
