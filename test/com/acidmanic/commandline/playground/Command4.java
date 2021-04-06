/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.playground;

import com.acidmanic.commandline.application.ExecutionEnvironment;
import com.acidmanic.commandline.application.ExecutionEnvironmentBuilder;
import com.acidmanic.commandline.commands.CommandBase;
import com.acidmanic.commandline.commands.TypeRegistery;
import com.acidmanic.commandline.commands.parameters.ParameterBuilder;
import com.acidmanic.commandline.utility.ArgumentValidationResult;

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
    protected void defineParameters(ParameterBuilder builder) {
        builder.named("intparam").described("Just pass an integer").indexAt(0)
                .mandatory().ofType(int.class)
                .newParam().named("TheString").described("String parameter")
                .optional().ofType(String.class);
    }
    
 
    
    
    
    @Override
    protected String getUsageDescription() {
        return "This command can take some arguments and process as a nested "
                + "command pattern";
    }

    @Override
    public void execute() {
        
        ExecutionEnvironment environment = new ExecutionEnvironmentBuilder()
                .withRegistery(new NestedTypeRegistery()).build();
        
        environment.execute(args);
        
        if (!environment.isHelpExecuted()){
            System.out.println("Command4 is executed");
        }
        
        info("getting integer parameter:");
        
        int value = this.getParameterValue("intparam");
        
        info("and ofcourse the value is: "+ value);
        
        info("getting string parameter:");
        
        String sparam = this.getParameterValue("TheString");
        
        info("and ofcourse the value is: "+ sparam);
        
    }

    @Override
    public ArgumentValidationResult validateArguments() {
        return new ArgumentValidationResult(1);
    }
    
    
    
}
