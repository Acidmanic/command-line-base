package com.acidmanic.commandline.commands.parameters;

import com.acidmanic.commandline.argumentparsing.ArgumentProperties;
import com.acidmanic.commandline.argumentparsing.ArgumentReadingStrategy;
import com.acidmanic.commandline.argumentparsing.NextToName;

public class OptionalParameter<T> extends ParameterBase<T> {

    public OptionalParameter(String name, Class<T> type) {
        super(name, type);
    }

    @Override
    public boolean isMandatory() {
        return false;
    }

    @Override
    protected ArgumentReadingStrategy getReadingStrategy() {
        return new NextToName();
    }

    @Override
    protected ArgumentProperties getArgumentProperties() {
        return new ArgumentProperties(this.getName(),-1);
    }

}