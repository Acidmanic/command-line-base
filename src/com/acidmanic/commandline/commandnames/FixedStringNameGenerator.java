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
public class FixedStringNameGenerator implements CommandNameGenerator{

   
    private final String value;

    public FixedStringNameGenerator(String value) {
        this.value = value;
    }
    
    

    @Override
    public String generateName() {
        return value;
    }

    
}
