/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.utility;

import com.acidmanic.consoletools.drawing.AsciiBorder;
import com.acidmanic.consoletools.drawing.Padding;
import com.acidmanic.consoletools.table.builders.TableBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import com.acidmanic.commandline.commands.Command;
import com.acidmanic.commandline.commands.TypeRegistery;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class HelpGenerator {

    private HashMap<String, String> helps;
    private final static int WIDTH_NAME = 24;
    private final static int WIDTH_DESCRIPTION = 100;

    public HelpGenerator(TypeRegistery registery) {

        this.helps = new HashMap<>();

        ArrayList<Class> commands = registery.getClasses(Command.class);

        for (Class commandClass : commands) {
            try {
                Command command = (Command) commandClass.newInstance();

                if (command.isVisible()) {

                    this.helps.put(command.getName(), command.getHelpDescription());
                }
            } catch (Exception e) {
            }
        }

    }

    public String generateHelp() {
        return generateHelp(null);
    }

    public String generateHelp(AsciiBorder border) {

        TableBuilder builder = new TableBuilder();

        Padding padding = new Padding(1, 0, 1, 0);

        for (String name : helps.keySet()) {

            String description = helps.get(name);

            builder.row().cell(name)
                    .maximumWidth(WIDTH_NAME)
                    .cell(description)
                    .maximumWidth(WIDTH_DESCRIPTION);
        }

        return builder.padAll(padding).borderAll(border)
                .build().render();
    }
}
