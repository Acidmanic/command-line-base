/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author diego
 */
public abstract class FractalCommandBase extends CommandBase{

    
    private final TypeRegistery subCommandsTypeRegistery;
    private final List<Command> subCommands;

    public FractalCommandBase() {
        this.subCommandsTypeRegistery = new TypeRegistery();
        
        this.addArgumentClasses(subCommandsTypeRegistery);
        
        this.subCommands = createInstance(subCommandsTypeRegistery);
    }
    
    
    protected abstract void addArgumentClasses(TypeRegistery registery);
    
    
    @Override
    protected String getArgumentsDesciption() {
        
        String argumentsDescription = "";
        
        for(Command argument:this.subCommands){
            argumentsDescription += argument.getHelpDescription() + "\n";
        }
        return argumentsDescription.trim();
    }

    @Override
    public void execute(String[] args) {
        
        CommandFactory factory = new CommandFactory(this.subCommandsTypeRegistery,this.getLogger());
        
        HashMap<Command,String[]> sublevels = factory.make(args, false);
        
        sublevels.forEach( (c,ar) -> c.execute(ar));
        
        execute();
    }
    
    
    protected abstract void execute();

    @Override
    public boolean hasArguments() {
        return true;
    }

    private List<Command> createInstance(TypeRegistery argumentsTypeRegistery) {
        
        List<Command>  arguments = new ArrayList<>();
        
        List<Class> types = argumentsTypeRegistery.getClasses(Command.class);
        
        for(Class type:types){
            try {
                
                Command command =  (Command) type.newInstance();
                
                arguments.add(command);
                
            } catch (Exception e) {
            }
        }
        return arguments;
    }
    
}
