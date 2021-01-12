/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.commands;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class NullCommand extends CommandBase {

    @Override
    public void execute() {
        log("No Such Command!");
    }

    @Override
    public String getDescription() {
        return "No Such Command!";
    }

    @Override
    public String getName() {
        return "*";
    }

    @Override
    public void setCreatorFactory(CommandFactory factory) {
    }

    @Override
    public CommandFactory getCreatorFactory() {
        return null;
    }

    @Override
    protected String getUsageString() {
        return "";
    }

}
