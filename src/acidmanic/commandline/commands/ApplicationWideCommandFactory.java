/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acidmanic.commandline.commands;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class ApplicationWideCommandFactory extends CommandFactory{
    
    private ApplicationWideCommandFactory() {
        super(ApplicationWideTypeRegistery.makeInstance());
    }
    
    private static ApplicationWideCommandFactory instance = null;
    
    public static synchronized ApplicationWideCommandFactory makeInstance(){
        if(ApplicationWideCommandFactory.instance == null){
            ApplicationWideCommandFactory.instance = new ApplicationWideCommandFactory();
        }
        return ApplicationWideCommandFactory.instance;
    }
    
}
