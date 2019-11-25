/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.playground;

import com.acidmanic.commandline.commands.CommandBase;
import com.acidmanic.commandline.commands.parameters.ParameterBuilder;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class Command1 extends CommandBase {
    
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName()+ " has been executed");
    }

    @Override
    public String getUsageString() {
        return "This is a long description for this comman. but this one does not contain new line charachter. "
                + "so this description is supposed break through wrapping process. on the debug line its not "
                + "happening any soon, maybe 100 chars is long enough. and can hold a long text to be seen.";
    }


    @Override
    protected void defineParameters(ParameterBuilder builder) {
        builder
            .named("zparam1").described("cool parameter").ofType(String.class).mandatory().indexAt(0)
            .newParam().named("a-man3").described("must be third").mandatory().indexAt(2).ofType(String.class)
            .newParam().named("man2").described("must be second").mandatory().indexAt(1).ofType(String.class)
            .newParam().named("param2").described("This is an optional parameter").ofType(String.class).optional();
    }
    
    
}
