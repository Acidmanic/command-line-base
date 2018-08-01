/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acidmanic.commandline.commands.subcommands;

import acidmanic.commandline.commands.ICommand;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public interface SubCommand extends ICommand {

    CommandValidationResult validate();
    boolean isHelp();
}
