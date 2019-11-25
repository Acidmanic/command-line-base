package com.acidmanic.commandline.commands;

import java.util.HashMap;

import com.acidmanic.commandline.application.ExecutionEnvironment;
import com.acidmanic.commandline.commandnames.ClassNameNameGenerator;
import com.acidmanic.commandline.commandnames.NameGenerator;
import com.acidmanic.commandline.commands.parameters.Parameter;
import com.acidmanic.commandline.commands.parameters.ParameterBuilder;
import com.acidmanic.commandline.utility.ArgumentValidationResult;
import com.acidmanic.commandline.utility.ConsoleLogger;
import com.acidmanic.commandline.utility.ParameterDescriber;

abstract public class CommandBase extends ConsoleLogger implements Command {

    protected String[] args = new String[]{};
    protected CommandFactory creatorFactory;
    private ExecutionEnvironment executionEnvironment;
    private NameGenerator nameGenerator;
    private HashMap<String,Parameter<?>> params = new HashMap<>();
    
    protected abstract String getUsageString();

    public CommandBase() {
        this.nameGenerator = new ClassNameNameGenerator(this.getClass());

        setupParameters();
    }

    protected final void setNameGenerator(NameGenerator generator){
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
        return new ParameterDescriber().describeParameters(this.params.values());
    }

    

    protected <T> T getParameterValue(String parameterName) {
        if(this.params.containsKey(parameterName)){
            return (T) this.params.get(parameterName).getValue();
        }

        return null;
    }

    protected boolean isParameterProvided(String parameterName){
        if(this.params.containsKey(parameterName)){
            return this.params.get(parameterName).hasValue();
        }

        return false;
    }

    @Override
    public void setArguments(String[] args) {
        this.args = args;
        
        fillupParameters();
    }

    private void setupParameters() {
        ParameterBuilder builder = new ParameterBuilder();

        this.defineParameters(builder);

        Parameter<?>[] result = builder.build();

        for (Parameter<?> param : result) {
            this.params.put(param.getName(), param);
        }
    }

    @Override
    public String getDescription() {
        return getUsageString() + " \n" + argumentsDesciption().trim();
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

    protected void usageError() {
        error("Argument error. usage:\n" + argumentsDesciption()+ "\n" +
                this.getName() + this.argumentsDesciption()+"\n"+
                this.getUsageString());
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

    private void fillupParameters() {
        for(Parameter<?> param : this.params.values()){
            param.parse(args);
        }
    }
    
}
