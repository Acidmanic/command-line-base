/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.playground;

import com.acidmanic.commandline.commands.Command;
import com.acidmanic.commandline.commands.Version;

/**
 *
 * @author diego
 */
public class VersionTest {
    
    
    public static void main(String[] args){
        
        Command version = new Version();
        
        version.execute();
    }
}
