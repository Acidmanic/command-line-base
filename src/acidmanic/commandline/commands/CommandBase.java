package acidmanic.commandline.commands;

import acidmanic.commandline.application.ExecutionEnvironment;
import acidmanic.commandline.utility.ArgumentValidationResult;
import com.acidmanic.consoletools.terminal.Terminal;
import com.acidmanic.consoletools.terminal.styling.TerminalStyle;
import com.acidmanic.consoletools.terminal.styling.TerminalStyles;

abstract public class CommandBase implements Command {

    protected String[] args = new String[]{};
    protected CommandFactory creatorFactory;
    private ExecutionEnvironment executionEnvironment;
    
    
    
    
    protected abstract String getUsageString();

    protected Boolean noArguments() {
        return noArguments(1);
    }

    @Override
    public void setCreatorFactory(CommandFactory factory) {
        this.creatorFactory = factory;
    }

    @Override
    public CommandFactory getCreatorFactory() {
        return this.creatorFactory;
    }

    protected Boolean noArguments(int numberOfArgumentsNeeded) {
        return (args == null || args.length < numberOfArgumentsNeeded);
    }

    protected int getArgsCount() {
        if (args == null || args.length == 0) {
            return 0;
        }
        return args.length;
    }

    /**
     * If your command takes any arguments, you can override this function and
     * return a declaration for them to be used in description. ex.:
     * &lt;date:yyyy-MM-dd&gt;
     *
     * @return
     */
    protected String declareArguments() {
        return "";
    }

    @Override
    public void setArguments(String[] args) {
        this.args = args;
    }

    @Override
    public String getdescription() {
        String argsdec = declareArguments().trim();
        String sep = "\n";
        if (argsdec == null || argsdec.length() == 0) {
            sep = "";
            argsdec = "";
        }
        return argsdec + sep + getUsageString();
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    /**
     * returns true if any error happens
     *
     * @param numberOfArguments
     * @return
     */
    protected boolean argumentCheck(int numberOfArguments) {
        if (noArguments(numberOfArguments)) {
            usageError();
            return true;
        }
        return false;
    }

    protected void log(String text) {
        Terminal t = new Terminal();
        t.resetScreenAttributes();
        System.out.println(text);
    }

    protected void usageError() {
        error("Argument error. usage:\n" + declareArguments()+ "\n" +
                this.getName() + this.declareArguments()+"\n"+
                this.getUsageString());
    }

    protected void error(String message) {
        Terminal t = new Terminal();
        t.setScreenAttributes(TerminalStyles.Error);
        System.out.println(message);
        t.resetScreenAttributes();
    }
    
    protected void warning(String message) {
        Terminal t = new Terminal();
        t.setScreenAttributes(TerminalStyles.Warning);
        System.out.println(message);
        t.resetScreenAttributes();
    }

    /**
     *
     * @return This function will concatenate all arguments together separating
     * with a space.
     */
    protected String allArgsAsCommand() {
        return allArgsAsCommand(0);
    }

    /**
     *
     * @param startIndex
     * @return This function will concatenate all arguments, starting from (and
     * including) <code>startIndex</code>.
     */
    protected String allArgsAsCommand(int startIndex) {
        String ret = "";
        String sep = "";
        for (int i = startIndex; i < args.length; i++) {
            ret += sep + args[i];
            sep = " ";
        }
        return ret;
    }

    
    @Override
    public ArgumentValidationResult validateArguments(){
        return new ArgumentValidationResult(0);
    }

    @Override
    public ExecutionEnvironment getExecutionEnvironment() {
        return executionEnvironment;
    }

    @Override
    public void setExecutionEnvironment(ExecutionEnvironment executionEnvironment) {
        this.executionEnvironment = executionEnvironment;
    }
    

    
    
}
