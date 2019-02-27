/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acidmanic.commandline.utility;

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
            int currentCase = Character.isLowerCase(c)?0:1;
            if (lastCase<currentCase){
                sb.append("-");
                c = Character.toLowerCase(c);
            }
            sb.append(c);
            lastCase = currentCase;
        }
        
        return sb.toString();
    }
}
