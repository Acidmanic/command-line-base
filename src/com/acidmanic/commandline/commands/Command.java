//
// Translated by CS2J (http://www.cs2j.com): 4/28/2016 9:15:43 PM
//
package com.acidmanic.commandline.commands;

import com.acidmanic.commandline.commands.context.ExecutionContext;
import com.acidmanic.lightweight.logger.Logger;

public interface Command {

    static final NullCommand NULLCOMMAND = new NullCommand();

    /**
     *
     * @param args arguments passed to this command
     */
    void execute(String[] args);
    
    boolean accepts(String name);
    
    boolean hasArguments();
    
    String getArgSplitRegEx();
    
    String getHelpDescription();

    void setCreatorFactory(CommandFactory factory);

    CommandFactory getCreatorFactory();

    boolean isVisible();

    void setLogger(Logger logger);
    
    void setContext(ExecutionContext context);
        
}
