/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acidmanic.commandline.commands.subcommands;

import acidmanic.commandline.commands.CommandBase;
import acidmanic.commandline.commands.CommandFactory;
import acidmanic.commandline.commands.ICommandFactory;
import acidmanic.commandline.utility.HelpGenerator;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class HelpSubCommand extends CommandBase implements SubCommand {

    @Override
    public void execute() {
        ICommandFactory factory = this.getCreatorFactory();
        if (factory instanceof CommandFactory) {
            HelpGenerator hg = new HelpGenerator(((CommandFactory) factory).getTypeRegistery());
            System.out.println(hg.generateHelp());
        }
    }

    @Override
    public String getdescription() {
        return "Shows this guid.";
    }

    @Override
    public CommandValidationResult validate() {
        return new CommandValidationResult(true, 0);
    }

    @Override
    public boolean isHelp() {
        return true;
    }

    @Override
    public String getName() {
        return "Help";
    }

    @Override
    protected String getUsageString() {return "";}

    

}
