/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acidmanic.commandline.utility;

import acidmanic.commandline.commands.subcommands.SubCommand;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class SubCommandResult {

    private List<SubCommand> subCommands;
    private List<String> otherArguments;
    private List<SubCommand> helpCommands;

    public SubCommandResult() {
        this.subCommands = new ArrayList<>();
        this.otherArguments = new ArrayList<>();
        this.helpCommands = new ArrayList<>();
    }

    public SubCommandResult(List<SubCommand> subCommands,
            List<String> otherArguments,
            List<SubCommand> helpCommands) {
        this.subCommands = subCommands;
        this.otherArguments = otherArguments;
        this.helpCommands = helpCommands;
    }

    public List<SubCommand> getSubCommands() {
        return subCommands;
    }

    public void setSubCommands(List<SubCommand> subCommands) {
        this.subCommands = subCommands;
    }

    public List<String> getOtherArguments() {
        return otherArguments;
    }

    public void setOtherArguments(List<String> otherArguments) {
        this.otherArguments = otherArguments;
    }

    public List<SubCommand> getHelpCommands() {
        return helpCommands;
    }

    public void setHelpCommands(List<SubCommand> helpCommands) {
        this.helpCommands = helpCommands;
    }

    public boolean containsHelp() {
        return this.helpCommands != null && this.helpCommands.size() > 0;
    }

}
