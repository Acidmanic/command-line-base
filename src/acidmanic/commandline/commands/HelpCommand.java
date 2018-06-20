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
                = new HelpGenerator(ApplicationWideTypeRegistery.makeInstance());
        Console.Write(generator.generateHelp());
        Console.WriteLine("");
    }

    @Override
    public String getdescription() {
        return "Shows This Help.";
    }

    @Override
    public String getName() {
        return "Help";
    }

}
