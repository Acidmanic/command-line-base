/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.playground;

import acidmanic.commandline.application.ExecutionEnvironment;
import acidmanic.commandline.commands.TypeRegistery;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class Playground {

    
    private static class TestTypeRegistery extends TypeRegistery{

        @SuppressWarnings("OverridableMethodCallInConstructor")
        public TestTypeRegistery() {
        
            registerClass(Command1.class);
            registerClass(Command2.class);
            registerClass(Command3.class);
            registerClass(Command4.class);
        
        }
        
        
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ExecutionEnvironment environment = new ExecutionEnvironment(
            new TestTypeRegistery());
        
        environment.execute(new String[]{"Command1","Command2","Command4","Argument1"});
        
        System.out.println("---------------------------");
        
        environment.execute(new String[]{"help"});
        
        System.out.println("---------------------------");
        
        environment.execute(new String[]{"Command4","help"});
    }
    
}
