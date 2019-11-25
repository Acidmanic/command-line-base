/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.commandnames;

import com.acidmanic.commandline.utility.CaseConvertor;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class SnakeCaseNameGenerator extends NamegeneratorComposite{

   

    public SnakeCaseNameGenerator(NameGenerator inner) {
        super(inner);
    }
    @Override
    protected String generateNameBasedOn(String innerName) {
        return new CaseConvertor().pascalToSnake(innerName);
    }

    
    
}
