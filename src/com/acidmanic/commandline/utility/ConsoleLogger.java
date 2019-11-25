package com.acidmanic.commandline.utility;

import com.acidmanic.consoletools.terminal.Terminal;
import com.acidmanic.consoletools.terminal.styling.TerminalStyles;

public class ConsoleLogger {


    protected void error(String message) {
        Terminal t = new Terminal();
        t.setScreenAttributes(TerminalStyles.Error);
        System.out.println(message);
        t.resetScreenAttributes();
    }
    
    protected void warning(String message) {
        Terminal t = new Terminal();
        t.setScreenAttributes(TerminalStyles.Warning);
        System.out.println(message);
        t.resetScreenAttributes();
    }
    
    protected void info(String message){
        Terminal t = new Terminal();
        t.setScreenAttributes(TerminalStyles.BlueInput);
        System.out.println(message);
        t.resetScreenAttributes();
    }
}