package com.acidmanic.commandline.commands;

import com.acidmanic.lightweight.logger.ConsoleLogger;
import com.acidmanic.lightweight.logger.Logger;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

public class CommandFactory {

    private HashMap<String, Class> typeList = new HashMap<>();
    private final ITypeRegistery typeRegistery;
    private final Logger logger;

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
        Command ret;
        String cmd = commandLine.getName().toLowerCase();
        if (typeList.containsKey(cmd)) {
            Class t = typeList.get(cmd);
            try {
                ret = (Command) t.newInstance();
                
                ret.setArguments(commandLine.getArgs());
                
                ret.setCreatorFactory(this);
                
                ret.setLogger(this.logger);
                
                return ret;
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
