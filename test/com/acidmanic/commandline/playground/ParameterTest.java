/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.playground;

import com.acidmanic.commandline.application.ExecutionEnvironment;
import com.acidmanic.commandline.commands.ApplicationWideTypeRegistery;

/**
 *
 * @author diego
 */
public class ParameterTest {
    
    
    public static void main(String[] args) {
        
        ApplicationWideTypeRegistery.makeInstance().registerClass(Command4.class);
        
        ExecutionEnvironment env = new ExecutionEnvironment();
        
        env.execute(new String[]{"command4","213","TheString","ImATestString"});
    }
}
