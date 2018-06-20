//
// Translated by CS2J (http://www.cs2j.com): 4/28/2016 9:15:43 PM
//

package acidmanic.commandline.commands;

import acidmanic.commandline.application.Console;

public class HelpCommand  extends CommandBase 
{
    @Override
    public void execute() {
        Console.Write(this.creatorFactory.getCommandsHelp());
        Console.WriteLine("");
    }

    @Override
    public String getdescription()  {
        return "Shows This Help.";
    }

    @Override
    public String getName(){
        return "Help";
    }

}


