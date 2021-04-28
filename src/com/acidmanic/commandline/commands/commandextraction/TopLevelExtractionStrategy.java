/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.commands.commandextraction;

import com.acidmanic.commandline.commands.Command;
import com.acidmanic.commandline.commands.TypeRegistery;
import com.acidmanic.commandline.utility.PutOrderedMap;
import java.util.Map;

/**
 *
 * @author diego
 */
public class TopLevelExtractionStrategy extends CommandExtractionStrategyBase {

    public TopLevelExtractionStrategy(TypeRegistery registery) {
        super(registery);
    }

    @Override
    public Map<Command, String[]> extract(String[] arguments) {

        Map<Command, String[]> commands = new PutOrderedMap<>();

        if (arguments != null && arguments.length > 0) {

            Command command = findCommandFor(arguments[0]);

            if (command != null) {

                String[] args = {};
                if (arguments.length > 1) {

                    args = new String[arguments.length - 1];
                    for (int i = 0; i < args.length; i++) {
                        args[i] = arguments[1 + i];
                    }
                }
                commands.put(command, args);
            }
        }
        return commands;
    }
}
