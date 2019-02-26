/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.playground;

import acidmanic.commandline.application.ExecutionEnvironment;
import acidmanic.commandline.commands.CommandBase;
import acidmanic.commandline.commands.TypeRegistery;
import acidmanic.commandline.utility.ArgumentValidationResult;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class Command4 extends CommandBase{

    private static class NestedTypeRegistery extends TypeRegistery{

        public NestedTypeRegistery() {
            registerClass(Argument1.class);
        
        }
        
        
    
    }
    
    
    @Override
    protected String getUsageString() {
        return "This command can take some arguments and process as a nested "
                + "command pattern";
    }

    @Override
    public void execute() {
        
        ExecutionEnvironment environment 
                = new ExecutionEnvironment(new NestedTypeRegistery());
        
        environment.execute(args);
        
        System.out.println("Command4 is executed");
        
    }

    @Override
    public ArgumentValidationResult validateArguments() {
        return new ArgumentValidationResult(1);
    }
    
    
    
}
