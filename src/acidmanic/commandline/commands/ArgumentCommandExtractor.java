/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acidmanic.commandline.commands;

import acidmanic.commandline.utility.SubCommand;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class ArgumentCommandExtractor {

    private final ICommandFactory commandFactory;

    private String[] getSubArray(String[] arguments, int index) {
        if (index < 0 || arguments == null || index >= arguments.length) {
            return new String[]{};
        }
        String[] ret = new String[arguments.length - index];
        for (int i = index; i < arguments.length; i++) {
            ret[i - index] = arguments[index];
        }
        return ret;
    }


    public ArgumentCommandExtractor(TypeRegistery commandTypes) {
        this.commandFactory = new CommandFactory(commandTypes);
    }

    public SubCommandResult processSubCommands(String[] arguments) {

        List<String> exteras = new ArrayList<>();
        List<SubCommand> subCommands = new ArrayList<>();

        int index = 0;
        while (index < arguments.length) {
            String arg = arguments[0];
            SubCommand command = getCommand(arguments, index);
            if (command == null || !command.isValid()) {
                exteras.add(arg);
                index += 1;
            } else {
                subCommands.add(command);
                index += 1 + command.getNumberOfPickedArguments();
            }
        }

        return new SubCommandResult(subCommands, exteras);
    }

    private SubCommand getCommand(String[] arguments, int index) {
        try {
            String[] args = getSubArray(arguments, index);
            return (SubCommand) this.commandFactory.makeCommand(args);
        } catch (Exception e) {
        }
        return null;
    }

}
