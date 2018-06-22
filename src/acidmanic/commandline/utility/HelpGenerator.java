/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acidmanic.commandline.utility;

import acidmanic.commandline.application.Console;
import acidmanic.commandline.commands.ICommand;
import acidmanic.commandline.commands.ITypeRegistery;
import com.acidmanic.consoletools.drawing.Padding;
import com.acidmanic.consoletools.drawing.ascii.AsciiBorder;
import com.acidmanic.consoletools.rendering.Renderable;
import com.acidmanic.consoletools.table.Bordered;
import com.acidmanic.consoletools.table.Cell;
import com.acidmanic.consoletools.table.Row;
import com.acidmanic.consoletools.table.Table;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class HelpGenerator {

    private HashMap<String, String> helps;
    private final static int WIDTH_NAME = 24;
    private final static int WIDTH_DESCRIPTION = 100;

    public HelpGenerator(ITypeRegistery registery) {
        this.helps = new HashMap<>();
        ArrayList<Class> commands = registery.getClasses(ICommand.class);
        for (Class commandClass : commands) {
            try {
                ICommand command = (ICommand) commandClass.newInstance();
                this.helps.put(command.getName(), command.getdescription());
            } catch (Exception e) {
            }
        }

    }

    public String generateHelp() {
        return generateHelp(null);
    }

    private Row createRow(String name, String description, AsciiBorder border) {
        Row row = new Row();
        Renderable nameCell = makeCell(name, WIDTH_NAME);
        Renderable descCell = makeCell(description, WIDTH_DESCRIPTION);
        if (border != null) {
            nameCell = new Bordered(nameCell, border);
            descCell = new Bordered(descCell, border);
        }
        row.getCells().add(nameCell);
        row.getCells().add(descCell);
        return row;
    }

    public String generateHelp(AsciiBorder border) {
        Table helpTabel = new Table();
        for (String name : helps.keySet()) {
            String description = helps.get(name);
            helpTabel.getRows()
                    .add(createRow(name, description, border));
        }
        return helpTabel.render();
    }

    private Renderable makeCell(String name, int width) {
        Cell cell = new Cell(name);
        cell.setMaximumWidth(width);
        cell.setPadding(new Padding(1, 0, 1, 1));
        return cell;
    }

}
