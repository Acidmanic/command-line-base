package com.acidmanic.commandline.commands;

import java.util.HashMap;

import com.acidmanic.commandline.application.ExecutionEnvironment;
import com.acidmanic.commandline.commandnames.ClassNameNameGenerator;
import com.acidmanic.commandline.commandnames.CommandNameGenerator;
import com.acidmanic.commandline.commands.parameters.Parameter;
import com.acidmanic.commandline.commands.parameters.ParameterBuilder;
import com.acidmanic.commandline.utility.ArgumentValidationResult;


import com.acidmanic.consoletools.terminal.Terminal;
import com.acidmanic.consoletools.terminal.styling.TerminalStyles;

abstract public class CommandBase implements Command {

    protected String[] args = new String[]{};
    protected CommandFactory creatorFactory;
    private ExecutionEnvironment executionEnvironment;
    private CommandNameGenerator nameGenerator;
    private HashMap<String,Parameter<?>> params;
    
    protected abstract String getUsageString();

    public CommandBase() {
        this.nameGenerator = new ClassNameNameGenerator(this.getClass());
    }

    protected final void setNameGenerator(CommandNameGenerator generator){
        this.nameGenerator = generator;
    }
    
    /***
     * Here you can add some parameters using the builder. it will be called before execution.
     * @param params
     */
    protected void defineParameters(ParameterBuilder builder){

    }
    
    
    protected final Boolean noArguments() {
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

    protected final Boolean noArguments(int numberOfArgumentsNeeded) {
        return (args == null || args.length < numberOfArgumentsNeeded);
    }

    protected final int getArgsCount() {
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
    protected String argumentsDesciption() {
        StringBuilder sb = new StringBuilder();

        for(Parameter<?> param : this.params.values()){
            String name = param.getName();
            if(!param.isMandatory()){
                name ="[" + name + "]";
            }

            sb.append('\n').append('\t').append(name).append('\t')
            .append(param.getDescription());
        }

        return sb.toString();
    }

    protected <T> T getParameterValue(String parameterName){
        if(this.params.containsKey(parameterName)){
            return (T) this.params.get(parameterName).getValue();
        }

        return null;
    }

    protected boolean isParameterProvided(String parameterName){
        return this.params.containsKey(parameterName);
    }

    @Override
    public void setArguments(String[] args) {
        this.args = args;

        ParameterBuilder builder = new ParameterBuilder();

        this.defineParameters(builder);

        Parameter<?>[] result = builder.build();
        
        for(Parameter<?> param : result){
            this.params.put(param.getName(), param);
        }
    }

    protected HashMap<String,Parameter<?>> getParameters(){
        return this.params;
    }

    @Override
    public String getdescription() {
        String argsdec = argumentsDesciption().trim();
        String sep = "\n";
        if (argsdec == null || argsdec.length() == 0) {
            sep = "";
            argsdec = "";
        }
        return argsdec + sep + getUsageString();
    }

    @Override
    public String getName() {
        return this.nameGenerator.generateName();
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
        error("Argument error. usage:\n" + argumentsDesciption()+ "\n" +
                this.getName() + this.argumentsDesciption()+"\n"+
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
    
    protected void info(String message){
        Terminal t = new Terminal();
        t.setScreenAttributes(TerminalStyles.BlueInput);
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
    public final ExecutionEnvironment getExecutionEnvironment() {
        return executionEnvironment;
    }

    @Override
    public void setExecutionEnvironment(ExecutionEnvironment executionEnvironment) {
        this.executionEnvironment = executionEnvironment;
    }
    
    
    protected ArgumentValidationResult enoughOrNothing(int argumentCount){
        if (args.length >=argumentCount){
            return new ArgumentValidationResult(argumentCount);
        }
        return new ArgumentValidationResult(0);
    }
    
    protected ArgumentValidationResult anyAvailable(int maximumNeeded){
        if (args.length < maximumNeeded){
            return new ArgumentValidationResult(args.length);
        }
        return new ArgumentValidationResult(maximumNeeded);
    }
    
    protected ArgumentValidationResult anyAvailable(){
        return new ArgumentValidationResult(args.length);
    }
    
}
