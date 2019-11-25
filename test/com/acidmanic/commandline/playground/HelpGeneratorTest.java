/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.playground;

import com.acidmanic.commandline.commands.ApplicationWideTypeRegistery;
import com.acidmanic.commandline.commands.CommandFactory;
import com.acidmanic.commandline.commands.TypeRegistery;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class HelpGeneratorTest {


    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        TypeRegistery registery = ApplicationWideTypeRegistery.makeInstance();
        
        registery.registerClass(Command1.class);
        
        registery.registerClass(Command2.class);
        
        registery.registerClass(Command3.class);
        
        new CommandFactory(registery).makeCommand("--help").execute();
    }

}
