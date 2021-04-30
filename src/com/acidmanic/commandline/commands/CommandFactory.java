package com.acidmanic.commandline.commands;

import com.acidmanic.commandline.commands.commandextraction.ArgumentLevelExtractionStrategy;
import com.acidmanic.commandline.commands.commandextraction.CommandExtractionStrategy;
import com.acidmanic.commandline.commands.commandextraction.TopLevelExtractionStrategy;
import com.acidmanic.commandline.commands.context.ExecutionContext;
import com.acidmanic.lightweight.logger.ConsoleLogger;
import com.acidmanic.lightweight.logger.Logger;
import java.util.Map;

public class CommandFactory {

    private final TypeRegistery typeRegistery;
    private final Logger logger;
    private final ExecutionContext context ; 
    
    public CommandFactory(TypeRegistery typeRegistery) {
        this(typeRegistery, new ConsoleLogger());
    }

    public CommandFactory(TypeRegistery typeRegistery, Logger logger) {
        this(typeRegistery,logger,null);
    }

    public CommandFactory(TypeRegistery typeRegistery, Logger logger, ExecutionContext context) {
        this.typeRegistery = typeRegistery;
        this.logger = logger;
        this.context = context;
    }
    
    public TypeRegistery getTypeRegistery() {
        return typeRegistery;
    }

    /**
     * This Method takes arguments array, and extracts every command with its
     * provided arguments
     *
     * @param args Input string array
     * @param topLevel If true, the first argument would be the command, and
     * others would be it's arguments, otherwise, each command might have one or
     * zero arguments which will be separated if the command represents any
     * delimiter
     * @return Returns the list of extracted commands with their arguments lists
     */
    public Map<Command, String[]> make(String[] args, boolean topLevel) {

        CommandExtractionStrategy strategy = topLevel?
                new TopLevelExtractionStrategy(typeRegistery):
                new ArgumentLevelExtractionStrategy(typeRegistery);
        
        Map<Command, String[]> commands = strategy.extract(args);
        
        commands.forEach((c,ar) -> c.setLogger(logger));
        
        commands.forEach((c,ar) -> c.setCreatorFactory(this));
        
        commands.forEach((c,ar) -> c.setContext(context));
        
        return commands;
    }
    
    public Map<Command, String[]> make(String[] args) {
        return this.make(args,false);
    }
}
