/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.commands;

import com.acidmanic.commandline.commands.context.ExecutionContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author diego
 * @param <TContext>
 */
public abstract class FractalCommandBase<TContext extends ExecutionContext> extends CommandBase {

    private final TypeRegistery subCommandsTypeRegistery;
    private final List<Command> subCommands;

    public FractalCommandBase() {
        this.subCommandsTypeRegistery = new TypeRegistery();

        this.addArgumentClasses(subCommandsTypeRegistery);

        this.subCommands = createInstance(subCommandsTypeRegistery);
    }

    protected abstract void addArgumentClasses(TypeRegistery registery);

    @Override
    protected String getArgumentsDesciption() {

        String argumentsDescription = "";

        for (Command argument : this.subCommands) {

            String argumentName = argument.getClass().getSimpleName();

            argumentsDescription += argumentName + ": "
                    + argument.getHelpDescription() + "\n";
        }
        return argumentsDescription.trim();
    }

    @Override
    public String getHelpDescription() {

        String help = "";

        if (hasArguments()) {
            help = "<arguments> ...\n";
        }
        help += getUsageDescription();

        if (hasArguments()) {

            String argumentsDescription = getArgumentsDesciption().trim();
            
            help = help + "\n\nArguments:\n" + argumentsDescription;
        }
        return help;
    }

    @Override
    public void execute(String[] args) {

        TContext context = createNewContext();

        CommandFactory factory = new CommandFactory(this.subCommandsTypeRegistery, this.getLogger(), context);

        HashMap<Command, String[]> sublevels = factory.make(args, false);

        for (Command command : sublevels.keySet()) {
            if (command.getClass().equals(Help.class)) {

                command.execute(sublevels.get(command));

                return;
            }
        }

        sublevels.forEach((c, ar) -> c.execute(ar));

        execute(context);
    }

    protected abstract void execute(TContext subCommandsExecutionContext);

    protected abstract TContext createNewContext();

    @Override
    public boolean hasArguments() {
        return true;
    }

    private List<Command> createInstance(TypeRegistery argumentsTypeRegistery) {

        List<Command> arguments = new ArrayList<>();

        List<Class> types = argumentsTypeRegistery.getClasses(Command.class);

        for (Class type : types) {
            try {

                Command command = (Command) type.newInstance();

                arguments.add(command);

            } catch (Exception e) {
            }
        }
        return arguments;
    }

}
