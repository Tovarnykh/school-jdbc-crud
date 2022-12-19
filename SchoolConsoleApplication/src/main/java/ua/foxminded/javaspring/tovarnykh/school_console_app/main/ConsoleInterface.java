package ua.foxminded.javaspring.tovarnykh.school_console_app.main;

import java.sql.SQLException;
import java.util.Scanner;

import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.CommandProvider;

/**
 *
 * @author Victor Tovarnykh
 * @version 0.15.0
 * @since 0.1.0
 */
public class ConsoleInterface {

    public static final String SEPARATOR = " -----------------------------------------\n";

    private static final String EXIT = "exit";
    private static final Scanner in = new Scanner(System.in);

    /**
     * Method name: ConsoleInterface
     *
     * Constructor that generates database before user can interact with it.
     */
    public ConsoleInterface() throws SQLException {
        CommandProvider.executeCommand(0);
    }

    /**
     * Method name: readLine
     *
     * @return (String) Read line. Inside the function: Read console line.
     */
    public static String readLine() {
        System.out.print(">");
        return ConsoleInterface.in.nextLine();

    }

    /**
     * Method name: readNumber
     *
     * @return (String) Read line. Inside the function: Reading console line, if
     *         it`s not a number repeat cycle.
     */
    public static int readNumber() {
        String input;
        do {
            System.out.print(">");
            input = ConsoleInterface.in.nextLine();
        } while (input.chars().noneMatch(Character::isDigit));
        return Integer.parseInt(input);
    }

    /**
     * Method name: exerciseChooser
     * @throws SQLException
     *
     * Inside the function: 1. Print Interface and call methods by chosen number.
     */
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
                    """);

            choice = readLine();
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

    /**
     * Method name: drawFindGroupsInterface
     * @throws SQLException
     *
     * Inside the function: 1. Print Interface and call command to interact with
     * database.
     */
    public void drawFindGroupsInterface() throws SQLException {
        System.out.print("""
                ╔════════════════════════════════════════╗
                ║Insert a number of students` to find    ║
                ║courses.                                ║
                ╟────────────────────────────────────────╢
                 in:
                 """);
        System.out.println(CommandProvider.executeCommand(1) + """
                ╚════════════════════════════════════════╝
                Enter any key to continue...
                """);
        readLine();
    }

    /**
     * Method name: drawFindStudentsInterface
     * @throws SQLException
     *
     * Inside the function: 1. Print Interface and call command to interact with
     * database.
     */
    public void drawFindStudentsInterface() throws SQLException {
        System.out.print("""
                ╔══════════════════════════════════════════╗
                ║Insert a course name to find it`s students║
                ╟──────────────────────────────────────────╢
                 in:
                 """);
        System.out.println(CommandProvider.executeCommand(2) + """
                ╚══════════════════════════════════════════╝
                Enter any key to continue...
                """);
        readLine();
    }

    /**
     * Method name: drawAddStudentsInterface
     * @throws SQLException
     *
     * Inside the function: 1. Print Interface and call command to interact with
     * database.
     */
    public void drawAddStudentsInterface() throws SQLException {
        System.out.print("""
                ╔══════════════════════════════════════════╗
                ║Insert: group id, first name and last_name║
                ║              to add student.             ║
                ╟──────────────────────────────────────────╢
                 in:
                 """);
        if (Boolean.parseBoolean(CommandProvider.executeCommand(3))) {
            System.out.println("\tStudent succsesfully added!");
        } else {
            System.out.println("There was a problem with adding student");
        }
        System.out.println("""
                ╚══════════════════════════════════════════╝
                Enter any key to continue...
                """);
        readLine();
    }

    /**
     * Method name: drawDeleteStudentsInterface
     * @throws SQLException
     *
     * Inside the function: 1. Print Interface and call command to interact with
     * database.
     */
    public void drawDeleteStudentsInterface() throws SQLException {
        System.out.print("""
                ╔══════════════════════════════════════════╗
                ║        Insert student Id to delete       ║
                ╟──────────────────────────────────────────╢
                 in:
                 """);
        if (Boolean.parseBoolean(CommandProvider.executeCommand(4))) {
            System.out.println("\tStudent succsesfully deleted!");
        } else {
            System.out.println("There was a problem with deleting student");
        }
        System.out.println("""
                ╚══════════════════════════════════════════╝
                Enter any key to continue...
                """);
        readLine();
    }

    /**
     * Method name: drawAddToCourseInterface
     * @throws SQLException
     *
     * Inside the function: 1. Print Interface and call command to interact with
     * database.
     */
    public void drawAddToCourseInterface() throws SQLException {
        System.out.print("""
                ╔══════════════════════════════════════════╗
                ║      Insert student Id and course Id     ║
                ║      to enroll student to the course.    ║
                ╟──────────────────────────────────────────╢
                 in:
                 """);
        if (Boolean.parseBoolean(CommandProvider.executeCommand(5))) {
            System.out.println("\tStudent succsesfully enrolled!");
        } else {
            System.out.println("There was a problem with enrolling student");
        }
        System.out.println("""
                ╚══════════════════════════════════════════╝
                Enter any key to continue...
                """);
        readLine();
    }

    /**
     * Method name: drawRemoveStudentInterface
     * @throws SQLException
     *
     * Inside the function: 1. Print Interface and call command to interact with
     * database.
     */
    public void drawRemoveStudentInterface() throws SQLException {
        System.out.print("""
                ╔══════════════════════════════════════════╗
                ║      Insert student Id and course Id     ║
                ║    to unenroll student from the course.  ║
                ╟──────────────────────────────────────────╢
                 in:
                 """);
        if (Boolean.parseBoolean(CommandProvider.executeCommand(6))) {
            System.out.println("\tStudent succsesfully unenrolled!");
        } else {
            System.out.println("There was a problem with unenrolling student");
        }
        System.out.println("""
                ╚══════════════════════════════════════════╝
                Enter any key to continue...
                """);
        readLine();
    }

    protected void finalize() {
        in.close();
    }

}