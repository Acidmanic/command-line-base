/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.playground;

import acidmanic.commandline.commands.ApplicationWideCommandFactory;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class HelpGeneratorTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ApplicationWideCommandFactory.makeInstance()
                .makeCommand("help").execute();
    }
    
}
