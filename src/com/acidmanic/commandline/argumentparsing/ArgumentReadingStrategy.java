package com.acidmanic.commandline.argumentparsing;



public interface ArgumentReadingStrategy {





    /**
     * This is the main implementarion of a specific way for reading an argument/value
     *  from the list of arguments.
     * @param argProp properties of the argument
     * @param args 
     * @return the string value of the argument
     */
    String read(ArgumentProperties argProp,String[] args) throws Exception;

    /**
     * 
     * @param argProp properties of the argument
     * @return a template of how argument should be provided
     */
    String describeProvision(ArgumentProperties argProp);
}