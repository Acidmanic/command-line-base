//
// Translated by CS2J (http://www.cs2j.com): 4/28/2016 9:15:43 PM
//
package acidmanic.commandline.commands;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

public class CommandFactory implements ICommandFactory {

    private HashMap<String, Class> typeList = new HashMap<>();
    private final ITypeRegistery typeRegistery;

    public CommandFactory(ITypeRegistery typeRegistery) {
        this.typeRegistery = typeRegistery;
        typeList = new HashMap<>();
        ArrayList<Class> allClasses = typeRegistery.getApplicationClasses();
        int fixedLength = 0;
        for (Class t : allClasses) {
            int mod = t.getModifiers();
            if (!Modifier.isAbstract(mod)
                    && !Modifier.isInterface(mod)
                    && isICommand(t)) {
                try {
                    String cmdName = ((ICommand) t.newInstance()).getName();
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
        return typeRegistery.isOfType(t, ICommand.class);
    }

    private String gethelp(Class t, int length) {
        try {
            ICommand cmd = (ICommand) t.newInstance();
            String name = cmd.getName();
            while (name.length() < length) {
                name += " ";
            }
            return name + "  " + cmd.getdescription();
        } catch (Exception e) {
            return "";
        }
    }

    public ITypeRegistery getTypeRegistery() {
        return typeRegistery;
    }
    
    

    private ICommand makeCommand(CommandLine commandLine) {
        ICommand ret;
        String cmd = commandLine.getName().toLowerCase();
        if (typeList.containsKey(cmd)) {
            Class t = typeList.get(cmd);
            try {
                ret = (ICommand) t.newInstance();
                ret.setArguments(commandLine.getArgs());
                ret.setCreatorFactory(this);
                return ret;
            } catch (Exception e) {
                return CommandBase.getNULL();
            }

        }

        return CommandBase.getNULL();
    }

    @Override
    public ICommand makeCommand(String commandLine) {
        return makeCommand(analyzeLine(commandLine));
    }

    @Override
    public ICommand makeCommand(String[] PromptArgs) {
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
