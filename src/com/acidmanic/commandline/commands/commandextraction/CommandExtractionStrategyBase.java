/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.commands.commandextraction;

import com.acidmanic.commandline.commands.Command;
import com.acidmanic.commandline.commands.TypeRegistery;
import java.util.List;

/**
 *
 * @author diego
 */
public abstract class CommandExtractionStrategyBase implements CommandExtractionStrategy {

    protected final TypeRegistery registery;
    protected final List<Class> commandTypes;

    public CommandExtractionStrategyBase(TypeRegistery registery) {
        this.registery = registery;
        this.commandTypes = registery.getClasses(Command.class);
    }

   
    protected Command findCommandFor(String commandName) {

        for (Class cmdType : this.commandTypes) {
            try {

                Command cmd = (Command) cmdType.newInstance();

                if (cmd.accepts(commandName)) {
                    return cmd;
                }
            } catch (Exception e) {
            }
        }
        return null;
    }
    
    protected String[] split(String argSplitRegEx, String rawArguments) {
        
        String[] args = {};
        
        if(rawArguments==null || rawArguments.length()==0){
            
            return args;
        }
        args = rawArguments.split(argSplitRegEx);
        
        return args;
    }

}
