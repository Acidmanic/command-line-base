/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.playground;

import com.acidmanic.commandline.commandnames.ClassNameNameGenerator;
import com.acidmanic.commandline.commandnames.DoubleDashedNameGenerator;
import com.acidmanic.commandline.commandnames.NameGeneratorBuilder;
import com.acidmanic.commandline.commandnames.SnakeCaseNameGenerator;
import com.acidmanic.commandline.commands.CommandBase;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class Argument1 extends CommandBase {

    public Argument1() {

        setNameGenerator(
                new NameGeneratorBuilder().seed(ClassNameNameGenerator.class)
                        .wrapedIn(SnakeCaseNameGenerator.class)
                        .wrapedIn(DoubleDashedNameGenerator.class)
                        .build(Argument1.class, "")
        );

    }

    @Override
    protected String getUsageDescription() {
        return "This is a dummy usage string";
    }

    @Override
    public void execute() {
        System.out.println("Argument1 is executed");
    }

}
