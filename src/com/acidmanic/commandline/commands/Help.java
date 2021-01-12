//
// Translated by CS2J (http://www.cs2j.com): 4/28/2016 9:15:43 PM
//
package com.acidmanic.commandline.commands;

import com.acidmanic.commandline.commandnames.NameGeneratorBuilder;
import com.acidmanic.commandline.utility.HelpGenerator;

public class Help extends CommandBase {

    public Help() {
        this.setNameGenerator(NameGeneratorBuilder.makeDoubleDashedSnakecaseClassnameNameGenerator(Help.class));

    }

    @Override
    public void execute() {
        HelpGenerator generator
                = new HelpGenerator(this.creatorFactory.getTypeRegistery());

        String help = generator.generateHelp();

        log(help);
    }

    @Override
    protected String getUsageString() {
        return "Prints this help.";
    }

}
