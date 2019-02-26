/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acidmanic.commandline.utility;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class ArgumentValidationResult {

    private int numberOfPickedArguments;

    public ArgumentValidationResult() {
    }

    public ArgumentValidationResult(int numberOfPickedArguments) {
        this.numberOfPickedArguments = numberOfPickedArguments;
    }

    public int getNumberOfPickedArguments() {
        return numberOfPickedArguments;
    }

    public void setNumberOfPickedArguments(int numberOfPickedArguments) {
        this.numberOfPickedArguments = numberOfPickedArguments;
    }
    
    

    
    
}
