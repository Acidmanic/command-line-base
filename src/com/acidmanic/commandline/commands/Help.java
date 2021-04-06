//
// Translated by CS2J (http://www.cs2j.com): 4/28/2016 9:15:43 PM
//
package com.acidmanic.commandline.commands;

import com.acidmanic.commandline.utility.HelpGenerator;

public class Help extends CommandBase {

    @Override
    public void execute(String[] args) {
        HelpGenerator generator
                = new HelpGenerator(this.creatorFactory.getTypeRegistery());

        String help = generator.generateHelp();

        log(help);
    }

    @Override
    protected String getUsageDescription() {
        return "Prints this help.";
    }

    @Override
    protected String getArgumentsDesciption() {
        return "";
    }

    @Override
    public boolean hasArguments() {
        return false;
    }

}
