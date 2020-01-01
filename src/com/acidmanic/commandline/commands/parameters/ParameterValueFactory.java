/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.commands.parameters;

/**
 *
 * @author 80116
 * @param <T>
 */
public interface ParameterValueFactory<T> {
    
    T convert(String sValue);
    
}
