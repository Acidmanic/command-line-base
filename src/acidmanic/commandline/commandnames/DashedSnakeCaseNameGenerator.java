/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acidmanic.commandline.commandnames;

import acidmanic.commandline.commands.Command;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class DashedSnakeCaseNameGenerator extends SnakeCaseNameGenerator{

    public DashedSnakeCaseNameGenerator(Class<? extends Command> type) {
        super(type);
    }

    @Override
    public String generateName() {
        return "-" + super.generateName(); 
    }

    
    
    
    
    
}
