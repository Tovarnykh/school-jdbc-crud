package ua.foxminded.javaspring.tovarnykh.school_console_app.commands;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Victor Tovarnykh
 * @version 0.15.0
 * @since 0.1.0
 */
public final class CommandProvider {

    public static Map<Integer, ControllerCommand> commandByCode = Arrays.asList(Command.values()).stream()
            .collect(Collectors.toMap(Command::getIndex, Command::getCommand));

    private CommandProvider() {

    }

}