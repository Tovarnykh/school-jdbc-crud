package ua.foxminded.javaspring.tovarnykh.school_console_app.commands;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Victor Tovarnykh
 * @version 0.15.0
 * @since 0.1.0
 */
public class CommandProvider {

    public static Map<Integer, Command> commandByCode = Arrays.asList(Commands.values()).stream()
            .collect(Collectors.toMap(Commands::getIndex, Commands::getCommand));

    public static Map<String, Command> commandByName = Arrays.asList(Commands.values()).stream()
            .collect(Collectors.toMap(Enum::toString, Commands::getCommand));

    private CommandProvider() {

    }

}