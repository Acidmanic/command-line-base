//
// Translated by CS2J (http://www.cs2j.com): 4/28/2016 9:15:43 PM
//
package com.acidmanic.commandline.commands;

import com.acidmanic.commandline.application.Console;
import com.acidmanic.commandline.commandnames.DoubleDashedSnakeCaseNameGenerator;
import com.acidmanic.commandline.utility.HelpGenerator;

public class Help extends CommandBase {

    public Help() {
        this.setNameGenerator(new DoubleDashedSnakeCaseNameGenerator(Help.class));
  
    }

    
    
    
    @Override
    public void execute() {
        HelpGenerator generator
                = new HelpGenerator(this.creatorFactory.getTypeRegistery());
        
        Console.Write(generator.generateHelp());
        Console.WriteLine("");
    }

    @Override
    protected String getUsageString() {
        return "Prints this help.";
    }
    

}
