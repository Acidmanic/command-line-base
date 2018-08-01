/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acidmanic.commandline.commands;

import acidmanic.commandline.commands.subcommands.ArgumentCommandExtractor;
import acidmanic.commandline.utility.SubCommandResult;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public abstract class ManagedArgumentCommandBase extends CommandBase {

    private final ITypeRegistery typeRegistery;

    public ManagedArgumentCommandBase(ITypeRegistery typeRegistery) {
        this.typeRegistery = typeRegistery;
    }

    protected SubCommandResult getSubCommands() {
        ArgumentCommandExtractor extractor = new ArgumentCommandExtractor(typeRegistery);
        return extractor.processSubCommands(args);
    }

    @Override
    public void execute() {
        SubCommandResult subCommandResult = getSubCommands();
        if (subCommandResult.containsHelp()) {
            subCommandResult.getHelpCommands().forEach(c -> c.execute());
            return;
        }
        subExecute(subCommandResult);
    }

    
    protected abstract void subExecute(SubCommandResult subCommandResult);
}
