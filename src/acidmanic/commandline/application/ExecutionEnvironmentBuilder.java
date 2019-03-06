/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acidmanic.commandline.application;

import acidmanic.commandline.commands.ApplicationWideTypeRegistery;
import acidmanic.commandline.commands.Command;
import acidmanic.commandline.commands.ITypeRegistery;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class ExecutionEnvironmentBuilder {
    
    private ExecutionDataRepository dataRepository;
    
    private ITypeRegistery typeRegistery;
    
    private Command executer;

    public ExecutionEnvironmentBuilder() {
        
        this.executer = null;
        
        this.typeRegistery = ApplicationWideTypeRegistery.makeInstance();
        
        this.dataRepository = new ExecutionDataRepository();
        
    }
    
    
    
    
    
    public ExecutionEnvironmentBuilder withRegistery(ITypeRegistery registery){
        this.typeRegistery = registery;
        return this;
    }
    
    public ExecutionEnvironmentBuilder withDataRepository(ExecutionDataRepository repository){
        this.dataRepository = repository;
        return this;
    }
    
    public ExecutionEnvironmentBuilder by(Command executer){
        this.executer = executer;
        return this;
    }
    
    public ExecutionEnvironment build(){
        return new ExecutionEnvironment(dataRepository, typeRegistery, executer);
    }
}
