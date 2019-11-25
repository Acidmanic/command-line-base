package com.acidmanic.commandline.argumentparsing;



public class OptionalByNameAndImmediateEqualSign implements ArgumentReadingStrategy {


    @Override
    public String read(ArgumentProperties argProp, String[] args) throws Exception {
        for(String arg : args){
            if(arg.startsWith(argProp.getName()+"=")){
                return arg.substring(argProp.getName().length()+1,arg.length());
            }
        }
        return null;
    }

    @Override
    public String describeProvision(ArgumentProperties argProp) {
        return argProp.getName()+"=<"+argProp.getName()+">";
    }



}