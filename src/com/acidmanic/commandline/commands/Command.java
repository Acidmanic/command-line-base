//
// Translated by CS2J (http://www.cs2j.com): 4/28/2016 9:15:43 PM
//
package com.acidmanic.commandline.commands;

import com.acidmanic.commandline.application.ExecutionEnvironment;
import com.acidmanic.commandline.commandnames.NameGenerator;
import com.acidmanic.commandline.utility.ArgumentValidationResult;
import com.acidmanic.lightweight.logger.Logger;

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

    void setNameGenerator(NameGenerator nameGenerator);

}
