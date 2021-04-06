//
// Translated by CS2J (http://www.cs2j.com): 4/28/2016 9:15:43 PM
//
package com.acidmanic.commandline.commands;

import com.acidmanic.commandline.commandnames.NameGeneratorBuilder;
import com.acidmanic.commandline.utility.HelpGenerator;

public class Help extends CommandBase {

    @Override
    public void execute() {
        HelpGenerator generator
                = new HelpGenerator(this.creatorFactory.getTypeRegistery_old());

        String help = generator.generateHelp();

        log(help);
    }

    @Override
    protected String getUsageString() {
        return "Prints this help.";
    }

}
