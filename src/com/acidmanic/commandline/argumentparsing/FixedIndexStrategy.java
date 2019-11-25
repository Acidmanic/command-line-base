package com.acidmanic.commandline.argumentparsing;



public class FixedIndexStrategy implements ArgumentReadingStrategy {

    @Override
    public String read(ArgumentProperties argProp, String[] args) {
        return args[argProp.getIndex()];
    }

    @Override
    public String describeProvision(ArgumentProperties argProp) {
        return "<"+argProp.getName()+">";
    }

}