/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acidmanic.commandline.commands.subcommands;

import acidmanic.commandline.commands.CommandFactory;
import acidmanic.commandline.commands.ICommand;
import acidmanic.commandline.commands.ICommandFactory;
import acidmanic.commandline.commands.ITypeRegistery;
import acidmanic.commandline.utility.SubCommandResult;
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
            ret[i - index] = arguments[i];
        }
        return ret;
    }

    public ArgumentCommandExtractor(ITypeRegistery commandTypes) {
        this.commandFactory = new CommandFactory(commandTypes);
    }

    public SubCommandResult processSubCommands(String[] arguments) {

        List<String> exteras = new ArrayList<>();
        List<SubCommand> subCommands = new ArrayList<>();
        List<SubCommand> helpCommands = new ArrayList<>();

        int index = 0;
        while (index < arguments.length) {
            String arg = arguments[index];
            CommandResult result = getValidatedCommand(arguments, index);
            if (!result.valid) {
                exteras.add(arg);
                index += 1;
            } else {
                if (result.command.isHelp()) {
                    helpCommands.add(result.command);
                } else {
                    subCommands.add(result.command);
                }
                index += 1 + result.numberOfPickedArguments;
            }
        }

        return new SubCommandResult(subCommands, exteras, helpCommands);
    }

    private class CommandResult {

        public boolean valid;
        public SubCommand command;
        public int numberOfPickedArguments;
    }

    private CommandResult getValidatedCommand(String[] arguments, int index) {

        CommandResult result = new CommandResult();
        result.valid = false;
        try {
            String[] args = getSubArray(arguments, index);
            ICommand command = this.commandFactory.makeCommand(args);
            if (command instanceof SubCommand) {
                result.command = (SubCommand) command;
                CommandValidationResult validationResult = result.command.validate();
                if (validationResult.isIsValid()) {
                    result.numberOfPickedArguments = validationResult.getNumberOfPickedArguments();
                    result.valid = true;
                    return result;
                }
            }
        } catch (Exception e) {
        }
        return result;
    }

}
