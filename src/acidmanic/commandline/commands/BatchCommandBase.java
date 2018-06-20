//
// Translated by CS2J (http://www.cs2j.com): 4/28/2016 9:15:43 PM
//

package acidmanic.commandline.commands;

import acidmanic.commandline.application.Console;
import java.util.ArrayList;


abstract public class BatchCommandBase  extends CommandBase 
{
    private ArrayList<String> commandLines = new ArrayList<>();
    private ICommandFactory factory;
    
    
    public BatchCommandBase(ICommandFactory factory) {
        commandLines = new ArrayList<>();
        this.factory=factory;
    }

    protected abstract void setupCommandLines(ArrayList<String> CommandLines)  ;
    
    
    @Override
    public void execute()  {
        ICommand c;
        setupCommandLines(commandLines);
        for (String command : commandLines)
        {
            //System.out.println("Executing " +command);
            c = factory.makeCommand(command);
            c.execute();
        }
        Console.WriteLine("Batch Command:" + this.getName() + " Executed Completely.");
    }

}


