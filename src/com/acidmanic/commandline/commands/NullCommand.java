/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.commands;

import com.acidmanic.commandline.commands.context.ExecutionContext;
import com.acidmanic.lightweight.logger.ConsoleLogger;
import com.acidmanic.lightweight.logger.Logger;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class NullCommand implements Command {

    private Logger logger = new ConsoleLogger();
    private CommandFactory factory = null;

    @Override
    public void execute(String[] args) {
        this.logger.log("No such command.");
    }

    @Override
    public boolean accepts(String name) {
        return false;
    }

    @Override
    public boolean hasArguments() {
        return false;
    }

    @Override
    public String getArgSplitRegEx() {
        return null;
    }

    @Override
    public String getHelpDescription() {
        return "";
    }

    @Override
    public void setCreatorFactory(CommandFactory factory) {
        this.factory = factory;
    }

    @Override
    public CommandFactory getCreatorFactory() {
        return this.factory;
    }

    @Override
    public boolean isVisible() {
        return false;
    }

    @Override
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void setContext(ExecutionContext context) {
    }

    @Override
    public String getName() {
        return "Null Command";
    }

}
