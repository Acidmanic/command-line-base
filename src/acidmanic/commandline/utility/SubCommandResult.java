/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acidmanic.commandline.utility;

import acidmanic.commandline.commands.ICommand;
import java.util.ArrayList;
import java.util.List;
import acidmanic.commandline.commands.subcommands.Validatable;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class SubCommandResult {

    private List<ICommand> subCommands;
    private List<String> otherArguments;
    private List<ICommand> helpCommands;

    public SubCommandResult() {
        this.subCommands = new ArrayList<>();
        this.otherArguments = new ArrayList<>();
        this.helpCommands = new ArrayList<>();
    }

    public SubCommandResult(List<ICommand> subCommands,
            List<String> otherArguments,
            List<ICommand> helpCommands) {
        this.subCommands = subCommands;
        this.otherArguments = otherArguments;
        this.helpCommands = helpCommands;
    }

    public List<ICommand> getSubCommands() {
        return subCommands;
    }

    public void setSubCommands(List<ICommand> subCommands) {
        this.subCommands = subCommands;
    }

    public List<String> getOtherArguments() {
        return otherArguments;
    }

    public void setOtherArguments(List<String> otherArguments) {
        this.otherArguments = otherArguments;
    }

    public List<ICommand> getHelpCommands() {
        return helpCommands;
    }

    public void setHelpCommands(List<ICommand> helpCommands) {
        this.helpCommands = helpCommands;
    }

    public boolean containsHelp() {
        return this.helpCommands != null && this.helpCommands.size() > 0;
    }

}
