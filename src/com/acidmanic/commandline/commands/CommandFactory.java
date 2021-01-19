package com.acidmanic.commandline.commands;

import com.acidmanic.commandline.commandnames.ClassNameNameGenerator;
import com.acidmanic.commandline.commandnames.NameGenerator;
import com.acidmanic.commandline.commandnames.NameGeneratorBuilder;
import com.acidmanic.lightweight.logger.ConsoleLogger;
import com.acidmanic.lightweight.logger.Logger;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

public class CommandFactory {

    private HashMap<String, Class> typeList = new HashMap<>();
    private final ITypeRegistery typeRegistery;
    private final Logger logger;
    private final NameGeneratorBuilder namegeneratorBuilder = new NameGeneratorBuilder();

    public CommandFactory(ITypeRegistery typeRegistery) {
        this(typeRegistery, new ConsoleLogger());
    }

    public CommandFactory(ITypeRegistery typeRegistery, Logger logger) {
        this.typeRegistery = typeRegistery;
        this.logger = logger;

        typeList = new HashMap<>();

        ArrayList<Class> allClasses = typeRegistery.getApplicationClasses();

        int fixedLength = 0;

        for (Class t : allClasses) {
            int mod = t.getModifiers();
            if (!Modifier.isAbstract(mod)
                    && !Modifier.isInterface(mod)
                    && isICommand(t)) {
                try {
                    String cmdName = ((Command) t.newInstance()).getName();
                    typeList.put(cmdName.toLowerCase(), t);
                    if (fixedLength < cmdName.length()) {
                        fixedLength = cmdName.length();
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    public NameGeneratorBuilder setupNameGenerator() {
        return this.namegeneratorBuilder;
    }

    private class CommandLine {

        private String name;
        private String[] args;

        public CommandLine(String name, String[] args) {
            this.name = name;
            this.args = args;
        }

        private CommandLine() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public String getName() {
            return name;
        }

        public String[] getArgs() {
            return args;
        }

    }

    private boolean isICommand(Class t) {
        return typeRegistery.isOfType(t, Command.class);
    }

    public ITypeRegistery getTypeRegistery() {
        return typeRegistery;
    }

    private Command makeCommand(CommandLine commandLine) {

        String commandName = commandLine.getName().toLowerCase();

        Command command;

        if (typeList.containsKey(commandName)) {

            Class commandType = typeList.get(commandName);

            try {
                command = (Command) commandType.newInstance();

                command.setArguments(commandLine.getArgs());

                command.setCreatorFactory(this);

                command.setLogger(this.logger);

                NameGenerator generator = this.namegeneratorBuilder.build(commandType, commandName);

                command.setNameGenerator(generator);

                return command;
            } catch (Exception e) {
                return Command.NULLCOMMAND;
            }

        }
        return Command.NULLCOMMAND;
    }

    public Command makeCommand(String commandLine) {
        return makeCommand(analyzeLine(commandLine));
    }

    public Command makeCommand(String[] PromptArgs) {
        return makeCommand(getnameAndArgs(PromptArgs));
    }

    private CommandLine getnameAndArgs(String[] PromptArgs) {
        if (PromptArgs == null || PromptArgs.length == 0) {
            return new CommandLine("ACOMMANDTHATDOSENOTEXIST&^%$YEAH!", new String[]{});
        }
        String args[] = new String[PromptArgs.length - 1];
        for (int i = 0; i < args.length; i++) {
            args[i] = PromptArgs[i + 1];
        }
        return new CommandLine(PromptArgs[0], args);
    }

    private CommandLine analyzeLine(String commandline) {
        String[] PromptArgs;
        if (commandline == null || commandline.length() == 0) {
            PromptArgs = null;
        } else {
            PromptArgs = commandline.split("\\s");
        }
        return getnameAndArgs(PromptArgs);

    }

}
