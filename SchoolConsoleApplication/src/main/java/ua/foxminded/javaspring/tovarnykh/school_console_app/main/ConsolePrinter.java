package ua.foxminded.javaspring.tovarnykh.school_console_app.main;

import java.sql.SQLException;
import java.util.Scanner;

import ua.foxminded.javaspring.tovarnykh.school_console_app.commands.Command;
import ua.foxminded.javaspring.tovarnykh.school_console_app.commands.CommandProvider;

/**
 *
 * @author Victor Tovarnykh
 * @version 0.15.0
 * @since 0.1.0
 */
public class ConsolePrinter {

    public static final String SEPARATOR = " -----------------------------------------\n";

    private static final String EXIT = "exit";
    private static Scanner in = new Scanner(System.in);

    /**
     * Method name: ConsoleInterface
     *
     * Constructor that generates database before user can interact with it.
     */
    public ConsolePrinter() throws SQLException {
        Command.INIT.getCommand().execute();
        Command.POPULATE.getCommand().execute();
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
        String choice = "";

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

            try {
                choice = readLine();
                if ((choice.chars().allMatch(Character::isDigit)) && (!choice.isEmpty())) {
                    int commandNumber = Integer.parseInt(choice);

                    if ((commandNumber > 0) && (commandNumber < Command.values().length - 1)) {
                        CommandProvider.commandByCode
                        .get(commandNumber)
                        .execute();
                    } else {
                        System.out.println("Such command is not available");
                    }

                } else if (EXIT.equalsIgnoreCase(choice)) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Bad input");
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
        return in.nextLine();
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

    public static void closeSection() {
        System.out.println("""
                ╚══════════════════════════════════════════╝
                Enter any key to continue...
                """);
        readLine();
    }

}