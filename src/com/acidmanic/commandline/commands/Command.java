//
// Translated by CS2J (http://www.cs2j.com): 4/28/2016 9:15:43 PM
//
package com.acidmanic.commandline.commands;

import com.acidmanic.commandline.application.ExecutionEnvironment;
import com.acidmanic.commandline.logging.Logger;
import com.acidmanic.commandline.utility.ArgumentValidationResult;

public interface Command {

    static final NullCommand NULLCOMMAND = new NullCommand();

    /**
     *
     */
    void execute();

    void setArguments(String[] args);

    String getDescription();

    String getName();

    void setCreatorFactory(CommandFactory factory);

    CommandFactory getCreatorFactory();

    boolean isVisible();

    ArgumentValidationResult validateArguments();

    void setExecutionEnvironment(ExecutionEnvironment executionEnvironment);

    ExecutionEnvironment getExecutionEnvironment();

    void setLogger(Logger logger);

}
