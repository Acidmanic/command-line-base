/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.logging;

import com.acidmanic.consoletools.terminal.Terminal;
import com.acidmanic.consoletools.terminal.styling.TerminalStyles;

/**
 *
 * @author diego
 */
public class ConsoleLogger implements Logger {
    
    @Override
    public void log(String text) {
        Terminal t = new Terminal();
        t.resetScreenAttributes();
        System.out.println(text);
    }

    @Override
    public void error(String message) {
        Terminal t = new Terminal();
        t.setScreenAttributes(TerminalStyles.Error);
        System.out.println(message);
        t.resetScreenAttributes();
    }
    
    @Override
    public void warning(String message) {
        Terminal t = new Terminal();
        t.setScreenAttributes(TerminalStyles.Warning);
        System.out.println(message);
        t.resetScreenAttributes();
    }
    
    @Override
    public void info(String message){
        Terminal t = new Terminal();
        t.setScreenAttributes(TerminalStyles.BlueInput);
        System.out.println(message);
        t.resetScreenAttributes();
    }
}
