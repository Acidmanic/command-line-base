//
// Translated by CS2J (http://www.cs2j.com): 4/28/2016 9:15:43 PM
//

package com.acidmanic.commandline.commands;

import com.acidmanic.commandline.application.Console;
import java.util.ArrayList;


abstract public class BatchCommandBase  extends CommandBase 
{
    private ArrayList<String> commandLines = new ArrayList<>();
    private final CommandFactory factory;
    
    
    public BatchCommandBase(CommandFactory factory) {
        commandLines = new ArrayList<>();
        this.factory=factory;
    }

    protected abstract void setupCommandLines(ArrayList<String> CommandLines)  ;
    
    
    @Override
    public void execute()  {
        Command c;
        setupCommandLines(commandLines);
        for (String command : commandLines)
        {
            c = factory.makeCommand(command);
            c.execute();
        }
        Console.WriteLine("Batch Command:" + this.getName() + " Executed Completely.");
    }

}


