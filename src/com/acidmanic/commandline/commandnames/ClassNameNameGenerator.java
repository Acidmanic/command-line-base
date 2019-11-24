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
public class ClassNameNameGenerator implements NameGenerator{
    
    private final Class<?> type;

    public ClassNameNameGenerator(Class<?> type) {
        this.type = type;
    }
    
    
    @Override
    public String generateName() {
        return type.getSimpleName();
    }
    
}
