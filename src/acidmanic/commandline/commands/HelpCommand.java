//
// Translated by CS2J (http://www.cs2j.com): 4/28/2016 9:15:43 PM
//
package acidmanic.commandline.commands;

import acidmanic.commandline.application.Console;
import acidmanic.commandline.utility.HelpGenerator;

public class HelpCommand extends CommandBase {

    @Override
    public void execute() {
        HelpGenerator generator
                = new HelpGenerator(this.creatorFactory.getTypeRegistery());
        
        Console.Write(generator.generateHelp());
        Console.WriteLine("");
    }

    @Override
    public String getName() {
        return "Help";
    }

    @Override
    protected String getUsageString() {
        return "Prints this help.";
    }

    @Override
    public boolean isHelp() {
        return true;
    }
    
    

}
