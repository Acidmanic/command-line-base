/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acidmanic.commandline.application;

import acidmanic.commandline.commands.ApplicationWideCommandFactory;
import acidmanic.commandline.commands.CommandFactory;
import acidmanic.commandline.commands.ICommand;
import acidmanic.commandline.commands.ICommandFactory;

/**
 *
 * @author diego
 */
public class CommandLineProgram {
    
    public static ICommandFactory factory=null;
      
    private static String getArgline(String[] args){
        if(args==null||args.length==0) return "Nothing!!";
        String ret ="";
        for(String arg : args) ret+=arg+" ";
        return ret;
    }
    
    public static void main(String[] args){
        if(factory==null)
            factory = ApplicationWideCommandFactory.makeInstance();
        
        
        ICommand command = factory.makeCommand(args);
        command.execute();
    }
}
