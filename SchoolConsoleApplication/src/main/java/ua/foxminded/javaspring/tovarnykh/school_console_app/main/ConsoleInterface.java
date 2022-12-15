package ua.foxminded.javaspring.tovarnykh.school_console_app.main;

import java.sql.SQLException;
import java.util.Scanner;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.CommandProvider;

public class ConsoleInterface {

    public static Scanner in;
    private static final String EXIT = "exit";

    public ConsoleInterface() throws SQLException {
        in = new Scanner(System.in);
        CommandProvider.executeCommand(0);
    }

    public void exerciseChooser() throws SQLException {
        String choice;

        do {
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

            choice = in.nextLine();
            if (EXIT.equalsIgnoreCase(choice)) {
                break;
            } else if (choice.equals("1")) {
                drawFindGroupsInterface();
            } else if (choice.equals("2")) {
                drawFindStudentsInterface();
            } else if (choice.equals("3")) {
                drawAddStudentsInterface();
            } else if (choice.equals("4")) {
                drawDeleteStudentsInterface();
            } else if (choice.equals("5")) {
                drawAddToCourseInterface();
            } else if (choice.equals("6")) {
                drawRemoveStudentInterface();
            }
        } while (!EXIT.equalsIgnoreCase(choice));

    }

    public void drawFindGroupsInterface() throws SQLException {
        System.out.print("""
                ╔════════════════════════════════════════╗
                ║Insert a number of students` to find    ║
                ║courses.                                ║
                ╟────────────────────────────────────────╢
                 in:""");
        System.out.println(CommandProvider.executeCommand(1) + """
                ╚════════════════════════════════════════╝
                Enter any key to continue...
                """);
        in.nextLine();
    }

    public void drawFindStudentsInterface() throws SQLException {
        System.out.print("""
                ╔══════════════════════════════════════════╗
                ║Insert a course name to find it`s students║
                ╟──────────────────────────────────────────╢
                 in:""");
        System.out.println(CommandProvider.executeCommand(2) + """
                ╚══════════════════════════════════════════╝
                Enter any key to continue...
                """);
        in.nextLine();
    }

    public void drawAddStudentsInterface() throws SQLException {
        System.out.print("""
                ╔══════════════════════════════════════════╗
                ║Insert: group id, first name and last_name║
                ║              to add student.             ║
                ╟──────────────────────────────────────────╢
                 in:""");
        if (Boolean.parseBoolean(CommandProvider.executeCommand(3)) == true) {
            System.out.println("\tStudent succsesfully added!");
        } else {
            System.out.println("There was a problem with adding student");
        }
        System.out.println("""
                ╚══════════════════════════════════════════╝
                Enter any key to continue...
                """);
        in.nextLine();
    }

    public void drawDeleteStudentsInterface() throws SQLException {
        System.out.print("""
                ╔══════════════════════════════════════════╗
                ║        Insert student Id to delete       ║
                ╟──────────────────────────────────────────╢
                 in:""");
        if (Boolean.parseBoolean(CommandProvider.executeCommand(4)) == true) {
            System.out.println("\tStudent succsesfully deleted!");
        } else {
            System.out.println("There was a problem with deleting student");
        }
        System.out.println("""
                ╚══════════════════════════════════════════╝
                Enter any key to continue...
                """);
        in.nextLine();
    }

    public void drawAddToCourseInterface() throws SQLException {
        System.out.print("""
                ╔══════════════════════════════════════════╗
                ║      Insert student Id and course Id     ║
                ║      to enroll student to the course.    ║
                ╟──────────────────────────────────────────╢
                 in:
                 """);
        if (Boolean.parseBoolean(CommandProvider.executeCommand(5)) == true) {
            System.out.println("\tStudent succsesfully enrolled!");
        } else {
            System.out.println("There was a problem with enrolling student");
        }
        System.out.println("""
                ╚══════════════════════════════════════════╝
                Enter any key to continue...
                """);
        in.nextLine();
    }

    public void drawRemoveStudentInterface() throws SQLException {
        System.out.print("""
                ╔══════════════════════════════════════════╗
                ║      Insert student Id and course Id     ║
                ║    to unenroll student from the course.  ║
                ╟──────────────────────────────────────────╢
                 in:
                 """);
        if (Boolean.parseBoolean(CommandProvider.executeCommand(6)) == true) {
            System.out.println("\tStudent succsesfully unenrolled!");
        } else {
            System.out.println("There was a problem with unenrolling student");
        }
        System.out.println("""
                ╚══════════════════════════════════════════╝
                Enter any key to continue...
                """);
        in.nextLine();
    }

    @Override
    protected void finalize() {
        in.close();
    }

}