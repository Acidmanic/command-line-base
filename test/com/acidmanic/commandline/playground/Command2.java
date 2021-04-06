/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.playground;

import com.acidmanic.commandline.commands.CommandBase;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class Command2 extends CommandBase {

    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName()+ " has been executed");
    }

    @Override
    public String getUsageDescription() {
        return "This is a preformated description." 
                + "\n for example:" + "\n" 
                + "\n\tThis is the first sub line" 
                + "\n\tThis is the second sub line" + "\n" 
                + "\nthis command also can have more sub lines" 
                + "\n\t ♦ like this" 
                + "\n\t ♦ or this.";
    }

}
