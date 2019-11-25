package com.acidmanic.commandline.argumentparsing;


public class NextToName implements ArgumentReadingStrategy {



    @Override
    public String read(ArgumentProperties argProp, String[] args) throws Exception {

        String lowerName = argProp.getName().toLowerCase();

        for(int i =0;i<args.length;i++){

            if(lowerName.equals(args[i].toLowerCase())){

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