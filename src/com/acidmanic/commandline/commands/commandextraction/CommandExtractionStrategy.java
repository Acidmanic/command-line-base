/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.commands.commandextraction;

import com.acidmanic.commandline.commands.Command;
import java.util.Map;

/**
 *
 * @author diego
 */
public interface CommandExtractionStrategy {
    
    
    Map<Command,String[]> extract(String[] arguments);
}
