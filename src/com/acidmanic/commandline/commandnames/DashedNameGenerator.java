/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.commandnames;


/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class DashedNameGenerator extends NamegeneratorComposite{


    public DashedNameGenerator(NameGenerator inner) {
        super(inner);
    }

    @Override
    protected String generateNameBasedOn(String innerName) {
        return "-" + innerName;
    }

    
    
    
    
    
}
