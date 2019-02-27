/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acidmanic.commandline.commandnames;

import acidmanic.commandline.commands.Command;
import acidmanic.commandline.utility.CaseConvertor;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class SnakeCaseNameGenerator extends ClassNameNameGenerator{

    public SnakeCaseNameGenerator(Class<? extends Command> type) {
        super(type);
    }

    @Override
    public String generateName() {
        
        String className = super.generateName();
        
        return new CaseConvertor().pascalToSnake(className);
    }

    
    
}
