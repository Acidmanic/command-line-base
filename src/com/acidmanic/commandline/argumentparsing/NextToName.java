package com.acidmanic.commandline.argumentparsing;


public class NextToName implements ArgumentReadingStrategy {



    @Override
    public String read(ArgumentProperties argProp, String[] args) throws Exception {
        for(int i =0;i<args.length;i++){
            if(argProp.getName().equals(args[i])){
                return args[i+1];
            }
        }
        return null;
    }

    @Override
    public String describeProvision(ArgumentProperties argProp) {
        return argProp.getName() + " <"+argProp.getName()+">";
    }

}