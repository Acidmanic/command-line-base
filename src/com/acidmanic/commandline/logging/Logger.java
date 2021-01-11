/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.logging;

/**
 *
 * @author diego
 */
public interface Logger {

    void error(String text);

    void warning(String text);

    void info(String text);

    void log(String text);
}
