/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acidmanic.commandline.application;

import java.util.HashMap;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class ExecutionDataRepository {
 
    
    
    private final HashMap<Object,Object> data;

    public ExecutionDataRepository() {
        this.data = new HashMap<>();
    }
    
    public <T> T get(Object key){
        if(this.data.containsKey(key)){
            return (T) this.data.get(key);
        }
        return null;
    }
    
    public <T> void set(Object key,T value){
        this.data.put(key, value);
    }
    
}
