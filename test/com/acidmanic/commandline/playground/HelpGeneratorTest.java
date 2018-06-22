/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.playground;

import acidmanic.commandline.commands.ApplicationWideCommandFactory;
import acidmanic.commandline.commands.ApplicationWideTypeRegistery;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class HelpGeneratorTest {


    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ApplicationWideTypeRegistery.makeInstance()
                .registerClass(Command1.class);
        ApplicationWideTypeRegistery.makeInstance()
                .registerClass(Command2.class);
        ApplicationWideTypeRegistery.makeInstance()
                .registerClass(Command3.class);
        
        ApplicationWideCommandFactory.makeInstance()
                .makeCommand("help").execute();
    }

}
