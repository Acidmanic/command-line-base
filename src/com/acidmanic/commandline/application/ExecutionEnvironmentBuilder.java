/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.application;

import com.acidmanic.commandline.commands.ApplicationWideTypeRegistery;
import com.acidmanic.commandline.commands.Command;
import com.acidmanic.commandline.commands.ITypeRegistery;

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
