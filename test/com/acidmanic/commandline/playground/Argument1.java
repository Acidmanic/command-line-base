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
public class Argument1 extends CommandBase {

    @Override
    protected String getUsageString() {
        return "This is a dummy usage string";
    }

    @Override
    public void execute() {
        System.out.println("Argument1 is executed");
    }
    
}