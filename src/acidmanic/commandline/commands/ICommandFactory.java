/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acidmanic.commandline.commands;

/**
 *
 * @author diego
 */
public interface ICommandFactory {

    String getCommandsHelp();

    ICommand makeCommand(String commandLine);

    ICommand makeCommand(String[] PromptArgs);
    
}
