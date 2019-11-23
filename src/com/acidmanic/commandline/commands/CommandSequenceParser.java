/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.commands;

import com.acidmanic.commandline.utility.ArgumentValidationResult;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class CommandSequenceParser {
    
    
    
    private final CommandFactory factory;

    public CommandSequenceParser(CommandFactory factory) {
        this.factory = factory;
    }
    
    
    
    
    public List<Command> parse(String[] arguments){
        
        ArrayList<Command> ret = new ArrayList<>();
        
        int index = 0;
        
        while(index <arguments.length){
            String[] args = getTrailingArray(arguments, index);
            
            Command c = this.factory.makeCommand(args);
            
            if (c instanceof NullCommand){
                index +=1;
            }else{
                
                ArgumentValidationResult result = c.validateArguments();
                
                index += 1 + result.getNumberOfPickedArguments();
                
                ret.add(c);
            }
        }
        
        return ret;
    }
    
    
    
    
    
    
    
    private String[] getTrailingArray(String[] arguments, int startIndex) {
        if (startIndex < 0 || arguments == null || startIndex >= arguments.length) {
            return new String[]{};
        }
        String[] ret = new String[arguments.length - startIndex];
        for (int i = startIndex; i < arguments.length; i++) {
            ret[i - startIndex] = arguments[i];
        }
        return ret;
    }
    
}
