package com.acidmanic.commandline.commands.parameters;

import com.acidmanic.commandline.argumentparsing.ArgumentProperties;
import com.acidmanic.commandline.argumentparsing.ArgumentReadingStrategy;
import com.acidmanic.commandline.argumentparsing.FixedIndexStrategy;
import com.acidmanic.commandline.utility.PrimaryConvertor;

public class MandatoryParameter<T> extends ParameterBase<T>{


    private int index;


    public MandatoryParameter(String name,int index,Class<T> type) {
        super(name,type);

        this.index = index;
         
    }

    @Override
    public boolean isMandatory() {
        return true;
    }

    @Override
    protected ArgumentReadingStrategy getReadingStrategy() {
        return new FixedIndexStrategy();
    }

    @Override
    protected ArgumentProperties getArgumentProperties() {
        return new ArgumentProperties(this.getName(),this.index);
    }

   

   

    





}