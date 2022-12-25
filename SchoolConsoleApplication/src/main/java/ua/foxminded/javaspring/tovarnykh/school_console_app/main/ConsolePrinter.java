package ua.foxminded.javaspring.tovarnykh.school_console_app.main;

import java.sql.SQLException;
import java.util.Scanner;

import ua.foxminded.javaspring.tovarnykh.school_console_app.commands.CommandProvider;
import ua.foxminded.javaspring.tovarnykh.school_console_app.dao.aspects.DatabaseProperties;

/**
 *
 * @author Victor Tovarnykh
 * @version 0.15.0
 * @since 0.1.0
 */
public class ConsolePrinter {

    public static final String SEPARATOR = " -----------------------------------------\n";

    private static final String EXIT = "exit";
    private static Scanner in = new Scanner(System.in);;

    /**
     * Method name: ConsoleInterface
     *
     * Constructor that generates database before user can interact with it.
     */
    public ConsolePrinter() throws SQLException {
        DatabaseProperties.readPropertyFile("databaseProperties.properties");
        CommandProvider.commandByCode.get(0).execute();
        CommandProvider.commandByCode.get(100).execute();
    }

    /**
     * Method name: exerciseChooser
     * 
     * @throws SQLException
     *
     *                      Inside the function: 1. Print Interface and call methods
     *                      by chosen number.
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
                    ║5 - Enroll student to the course.        ║
                    ║6 - Remove the student                   ║
                    ║    from one of their courses.           ║
                    ║                                         ║
                    ║exit - to Exit                           ║
                    ╚═════════════════════════════════════════╝
                    """);

            choice = readLine();
            
            if (choice.chars().allMatch(Character::isDigit) && !choice.isEmpty()) {
                CommandProvider.commandByCode.get(Integer.valueOf(choice)).execute();
            } else if (EXIT.equalsIgnoreCase(choice)) {
                break;
            }
        } while (!EXIT.equalsIgnoreCase(choice));
        in.close();
    }

    /**
     * Method name: readLine
     *
     * @return (String) Read line. Inside the function: Read console line.
     */
    public static String readLine() {
        System.out.print(">");
        return ConsolePrinter.in.nextLine();
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
            input = ConsolePrinter.in.nextLine();
        } while (input.chars().noneMatch(Character::isDigit));
        return Integer.parseInt(input);
    }
    
    public static void closeWindow() {
        System.out.println("""
                ╚══════════════════════════════════════════╝
                Enter any key to continue...
                """);
        readLine();
    }

}