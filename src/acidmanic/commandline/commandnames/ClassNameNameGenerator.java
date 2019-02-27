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
public class ClassNameNameGenerator implements CommandNameGenerator{
    
    private final Class<? extends Command> type;

    public ClassNameNameGenerator(Class<? extends Command> type) {
        this.type = type;
    }
    
    
    @Override
    public String generateName() {
        return type.getSimpleName();
    }
    
}
