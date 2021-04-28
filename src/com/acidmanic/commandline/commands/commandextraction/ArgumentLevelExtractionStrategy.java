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
public class ArgumentLevelExtractionStrategy extends CommandExtractionStrategyBase {

    public ArgumentLevelExtractionStrategy(TypeRegistery registery) {
        super(registery);
    }

    @Override
    public Map<Command, String[]> extract(String[] arguments) {

        Map<Command, String[]> commands = new PutOrderedMap<>();

        if (arguments != null) {

            int argIndex = 0;

            while (argIndex < arguments.length) {

                String argument = arguments[argIndex];

                Command command = findCommandFor(argument);

                argIndex += 1;

                if (command != null) {

                    String[] args = {};

                    if (command.hasArguments()) {

                        if (argIndex < arguments.length) {

                            String rawArguments = arguments[argIndex];

                            argIndex += 1;

                            args = split(command.getArgSplitRegEx(), rawArguments);
                        }
                    }
                    commands.put(command, args);
                }
            }
        }
        return commands;
    }
}
