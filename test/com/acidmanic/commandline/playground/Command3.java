/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.playground;

import java.io.File;
import java.util.Date;

import com.acidmanic.commandline.commands.CommandBase;
import com.acidmanic.commandline.commands.parameters.ParameterBuilder;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class Command3 extends CommandBase {

    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName()+ " has been executed");

        File input = getParameterValue("input-file");

        info("input file: " + input.toPath().normalize().toAbsolutePath().toString());

        if(this.isParameterProvided("pashmak-date")){
            info("pashmak date: " + this.getParameterValue("pashmak-date"));
        }
    }


    @Override
    protected void defineParameters(ParameterBuilder builder) {
        builder.named("input-file").described("the path to input file, this can be either relative or absolute.")
        .ofType(File.class).indexAt(0).mandatory()
        .newParam().named("pashmak-date").ofType(Date.class).described("the day it's been pashmaked.").optional();
    }

    @Override
    public String getUsageString() {
        return "This is a preformated description. this command has a long name which should break. it also will print the absolute path of given input file.";
    }

    @Override
    public String getName() {
        return "a-very-large-name-for-a-command";
    }
    
    

}
