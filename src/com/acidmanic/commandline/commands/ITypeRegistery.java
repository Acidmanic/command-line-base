/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.commands;

import java.util.ArrayList;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public interface ITypeRegistery {

    ArrayList<Class> getApplicationClasses();

    ArrayList<String> getApplicationClassesNames();

    ArrayList<Class> getClasses(Class superClass);

    boolean hasImplemented(Class subClass, Class superClass);

    boolean isOfType(Class subClass, Class superClass);

    void registerClass(Class refType);

    void registerClass(String fullName);
    
}
