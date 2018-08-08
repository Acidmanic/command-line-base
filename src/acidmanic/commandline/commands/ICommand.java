//
// Translated by CS2J (http://www.cs2j.com): 4/28/2016 9:15:43 PM
//
package acidmanic.commandline.commands;

public interface ICommand {

    
    static final NullCommand NULLCOMMAND = new NullCommand();
    /**
     *
     */
    void execute();

    void setArguments(String[] args);

    String getdescription();

    String getName();

    void setCreatorFactory(ICommandFactory factory);

    ICommandFactory getCreatorFactory();

    boolean isVisible();
    
    boolean isHelp();
}
