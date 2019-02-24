/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acidmanic.commandline.commands.subcommands;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class CommandValidationResult {
    private boolean  isValid;
    private int numberOfPickedArguments;

    public CommandValidationResult() {
    }

    public CommandValidationResult(boolean isValid, int numberOfPickedArguments) {
        this.isValid = isValid;
        this.numberOfPickedArguments = numberOfPickedArguments;
    }

    public boolean isIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public int getNumberOfPickedArguments() {
        return numberOfPickedArguments;
    }

    public void setNumberOfPickedArguments(int numberOfPickedArguments) {
        this.numberOfPickedArguments = numberOfPickedArguments;
    }
    
    
    
    
}
