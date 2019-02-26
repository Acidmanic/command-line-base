/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.playground;

import acidmanic.commandline.commands.CommandBase;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class Command3 extends CommandBase {

    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName()+ " has been executed");
    }

    @Override
    public String getUsageString() {
        return "This is a preformated description. this command has a long name which should break";
    }

    @Override
    public String getName() {
        return "a-very-large-name-for-a-command";
    }
    
    

}
