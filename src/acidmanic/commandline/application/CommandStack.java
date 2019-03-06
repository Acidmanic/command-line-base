/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acidmanic.commandline.application;

import acidmanic.commandline.commands.Command;
import java.util.Stack;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class CommandStack {
    
    private static final Stack<Command> commands=new Stack<>();
    
    static void push(Command command){
        commands.push(command);
    }
    
    /**
     * This will return last command who has created an execution environment to
     * execute more subcommands. if this method is called on a top level command,
     *  it will return a NULLCommand. For this to work correctly, you need to 
     * instantiate The ExecutionEnvironment with the running command, like this:
     *  <code> ExecutionEnvironment environment = new ExecutionEnvironment(this);
     * </code>
     * @return 
     */
    public static Command lastMasterCommand(){
        if(commands.isEmpty()){
            return Command.NULLCOMMAND;
        }
        return commands.peek();
    }
    
    static void pop(){
        commands.pop();
    }
}
