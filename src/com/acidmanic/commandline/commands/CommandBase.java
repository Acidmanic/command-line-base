package com.acidmanic.commandline.commands;

import com.acidmanic.commandline.commands.context.ExecutionContext;
import com.acidmanic.lightweight.logger.ConsoleLogger;
import com.acidmanic.lightweight.logger.Logger;

public abstract class CommandBase implements Command {

    protected CommandFactory creatorFactory;
    private Logger logger;
    private ExecutionContext context;

    /**
     * Describe what the command does.
     *
     * @return
     */
    protected abstract String getUsageDescription();

    /**
     * Describe every argument, in one line
     *
     * @return
     */
    protected abstract String getArgumentsDesciption();

    public CommandBase() {
        this.logger = new ConsoleLogger();
    }

    @Override
    public boolean accepts(String name) {
        return this.getClass().getSimpleName()
                .equalsIgnoreCase(name);
    }

    @Override
    public String getArgSplitRegEx() {
        return "\\s";
    }

    @Override
    public void setCreatorFactory(CommandFactory factory) {
        this.creatorFactory = factory;
    }

    @Override
    public CommandFactory getCreatorFactory() {
        return this.creatorFactory;
    }

    @Override
    public String getHelpDescription() {

        String argumentsDescription = getArgumentsDesciption().trim();

        argumentsDescription = indentDescription(argumentsDescription);

        String help = getUsageDescription();

        if (argumentsDescription.length() > 0) {

            help = help + "\n" + argumentsDescription;
        }
        return help;
    }

    protected String indentDescription(String description) {
        return description.replaceAll("\\n", "\n  ");
    }

    @Override
    public void setContext(ExecutionContext context) {
        this.context = context;
    }

    protected <TContext> TContext getContext() {
        return (TContext) context;
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
    
    @Override
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    protected void error(String text) {
        this.logger.error(text);
    }

    protected void warning(String text) {
        this.logger.warning(text);
    }

    protected void info(String text) {
        this.logger.info(text);
    }

    protected void log(String text) {
        this.logger.log(text);
    }

    protected Logger getLogger() {
        return this.logger;
    }
}
