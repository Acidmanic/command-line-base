/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.utility;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class CaseConvertor {
    
    
    
    public String pascalToSnake(String value){
        
        char[] chars = value.toCharArray();
        
        StringBuilder sb = new StringBuilder();
        
        int lastCase = 1;
        
        for(char c : chars){
            int currentCase = Character.isUpperCase(c)?1:0;
            c = Character.toLowerCase(c);
            
            if (lastCase<currentCase){
                sb.append("-");    
            }
            sb.append(c);
            lastCase = currentCase;
        }
        
        return sb.toString();
    }
}
