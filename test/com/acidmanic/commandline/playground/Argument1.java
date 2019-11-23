/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.playground;

import com.acidmanic.commandline.commandnames.DoubleDashedSnakeCaseNameGenerator;
import com.acidmanic.commandline.commands.CommandBase;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class Argument1 extends CommandBase {

    public Argument1() {
        setNameGenerator(new DoubleDashedSnakeCaseNameGenerator(this.getClass()));
    
    }

    
    
    
    @Override
    protected String getUsageString() {
        return "This is a dummy usage string";
    }

    @Override
    public void execute() {
        System.out.println("Argument1 is executed");
    }
    
}
