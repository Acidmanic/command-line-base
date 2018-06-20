//
// Translated by CS2J (http://www.cs2j.com): 4/28/2016 9:15:43 PM
//
package acidmanic.commandline.commands;

import acidmanic.commandline.application.Console;

abstract public class CommandBase implements ICommand {

    protected static final NullCommand NULLCOMMAND = new NullCommand();
    protected String[] args = new String[]{};
    protected ICommandFactory creatorFactory;

    public static ICommand getNULL() {
        return NULLCOMMAND;
    }

    protected Boolean noArguments() {
        return noArguments(1);
    }

    @Override
    public void setCreatorFactory(ICommandFactory factory) {
        this.creatorFactory=factory;
    }

    @Override
    public ICommandFactory getCreatorFactory() {
        return this.creatorFactory;
    }

    protected Boolean noArguments(int numberOfArgumentsNeeded) {
        return (args == null || args.length < numberOfArgumentsNeeded);
    }

    protected int readIntArg(int index, int def) {
        try {
            return Integer.parseInt(args[index]);
        } catch (Exception __dummyCatchVar0) {
            return def;
        }

    }

    protected int getArgsCount() {
        if (args == null || args.length == 0) {
            return 0;
        }
        return args.length;
    }

    public static class NullCommand extends CommandBase {

        @Override
        public void execute() {
            Console.WriteLine("No Such Command!");
        }

        @Override
        public String getdescription() {
            return "No Such Command!";
        }

        @Override
        public String getName() {
            return "*";
        }

        @Override
        public void setCreatorFactory(ICommandFactory factory) {
        }

        @Override
        public ICommandFactory getCreatorFactory() {
            return null;
        }

    }

    @Override
    public abstract void execute();

    @Override
    public void setArguments(String[] args) {
        this.args = args;
    }

    @Override
    public abstract String getdescription();

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

}
