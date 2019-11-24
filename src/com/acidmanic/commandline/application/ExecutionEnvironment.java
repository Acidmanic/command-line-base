/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.application;

import com.acidmanic.commandline.commands.ApplicationWideTypeRegistery;
import com.acidmanic.commandline.commands.CommandFactory;
import com.acidmanic.commandline.commands.ITypeRegistery;
import com.acidmanic.commandline.commands.Command;
import com.acidmanic.commandline.commands.CommandSequenceParser;
import com.acidmanic.commandline.commands.Help;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class ExecutionEnvironment {
    
    
    private ExecutionDataRepository dataRepository;
    
    private ITypeRegistery typeRegistery;

    private boolean helpExecuted;
    
    private int exitCode;
    
    private int numberOfExecutedCommands;
    
        
    
    
    public ExecutionEnvironment() {
        
        this.dataRepository = new ExecutionDataRepository();
        
        this.typeRegistery = ApplicationWideTypeRegistery.makeInstance();
        
        this.exitCode =0;
        
        CommandStack.push(null);
        
    }

    public ExecutionEnvironment(ITypeRegistery typeRegistery) {
        this.dataRepository = new ExecutionDataRepository();
        
        this.typeRegistery = typeRegistery;
        
        this.exitCode =0;
        
        CommandStack.push(null);
    }
    
    public ExecutionEnvironment(ITypeRegistery typeRegistery,Command parentExecuter) {
        this.dataRepository = new ExecutionDataRepository();
        
        this.typeRegistery = typeRegistery;
        
        this.exitCode =0;
        
        CommandStack.push(parentExecuter);
    }
    
    

    public ExecutionEnvironment(ExecutionDataRepository dataRepository, ITypeRegistery typeRegistery,
            Command parentExecuter) {
        
        this.dataRepository = dataRepository;
    
        this.typeRegistery = typeRegistery;
        
        CommandStack.push(parentExecuter);
    }

    public ExecutionDataRepository getDataRepository() {
        return dataRepository;
    }

    public void setDataRepository(ExecutionDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public ITypeRegistery getTypeRegistery() {
        return typeRegistery;
    }

    public void setTypeRegistery(ITypeRegistery typeRegistery) {
        this.typeRegistery = typeRegistery;
    }
    
    
    public void execute(String[] args){
                
        CommandFactory factory = new CommandFactory(this.typeRegistery);
        
        CommandSequenceParser parser = new CommandSequenceParser(factory);
        
        List<Command> commands = parser.parse(args);
        
        commands = filterHelp(commands);
        
        this.numberOfExecutedCommands = commands.size();
        
        for(Command c:commands){
            
            c.setExecutionEnvironment(this);
            
            c.execute();
            
            if(c instanceof Help){
                this.helpExecuted = true;
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            CommandStack.pop();
        } finally {
            super.finalize();
        }
    }
    
    
    

    private List<Command> filterHelp(List<Command> commands) {
        ArrayList<Command> ret = new ArrayList<>();
        
        for(Command c :commands){
            if(c instanceof Help){
                ArrayList<Command> help = new ArrayList<>();
                help.add(c);
                return help;
            }
            ret.add(c);
        }
        return ret;
    }

    public boolean isHelpExecuted() {
        return helpExecuted;
    }

    public int getNumberOfExecutedCommands() {
        return numberOfExecutedCommands;
    }

    public int getExitCode() {
        return exitCode;
    }

    public void setExitCode(int exitCode) {
        this.exitCode = exitCode;
    }
    
}