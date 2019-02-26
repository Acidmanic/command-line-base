/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acidmanic.commandline.application;

import acidmanic.commandline.commands.ApplicationWideTypeRegistery;
import acidmanic.commandline.commands.CommandFactory;
import acidmanic.commandline.commands.ITypeRegistery;
import acidmanic.commandline.commands.Command;
import acidmanic.commandline.commands.CommandSequenceParser;
import acidmanic.commandline.commands.HelpCommand;
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
    
    private int numberOfExecutedCommands;
    
    public ExecutionEnvironment() {
        
        this.dataRepository = new ExecutionDataRepository();
        
        this.typeRegistery = ApplicationWideTypeRegistery.makeInstance();
    }
    
    

    public ExecutionEnvironment(ExecutionDataRepository dataRepository) {
        
        this.dataRepository = dataRepository;
        
        this.typeRegistery = ApplicationWideTypeRegistery.makeInstance();
    }

    public ExecutionEnvironment(ITypeRegistery typeRegistery) {
        
        this.typeRegistery = typeRegistery;
        
        this.dataRepository = new ExecutionDataRepository();
    }

    public ExecutionEnvironment(ExecutionDataRepository dataRepository, ITypeRegistery typeRegistery) {
        
        this.dataRepository = dataRepository;
    
        this.typeRegistery = typeRegistery;
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
            
            if(c instanceof HelpCommand){
                this.helpExecuted = true;
            }
        }
    }

    private List<Command> filterHelp(List<Command> commands) {
        ArrayList<Command> ret = new ArrayList<>();
        
        for(Command c :commands){
            if(c instanceof HelpCommand){
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
  
    
}
