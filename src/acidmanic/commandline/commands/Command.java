//
// Translated by CS2J (http://www.cs2j.com): 4/28/2016 9:15:43 PM
//
package acidmanic.commandline.commands;

import acidmanic.commandline.application.ExecutionEnvironment;
import acidmanic.commandline.utility.ArgumentValidationResult;

public interface Command {

    
    static final NullCommand NULLCOMMAND = new NullCommand();
    /**
     *
     */
    void execute();

    void setArguments(String[] args);

    String getdescription();

    String getName();

    void setCreatorFactory(CommandFactory factory);

    CommandFactory getCreatorFactory();

    boolean isVisible();
    
    boolean isHelp();
    
    ArgumentValidationResult validateArguments();
    
    void setExecutionEnvironment(ExecutionEnvironment executionEnvironment);
    
    ExecutionEnvironment getExecutionEnvironment();
    
}
