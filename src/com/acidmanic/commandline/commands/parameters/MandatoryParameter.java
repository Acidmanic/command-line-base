package com.acidmanic.commandline.commands.parameters;

import com.acidmanic.commandline.argumentparsing.ArgumentProperties;
import com.acidmanic.commandline.argumentparsing.ArgumentReadingStrategy;
import com.acidmanic.commandline.argumentparsing.FixedIndexStrategy;

public class MandatoryParameter<T> extends ParameterBase<T> {

    private int index;

    public MandatoryParameter(String name, int index) {
        super(name);

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
        return new ArgumentProperties(this.getName(), this.index);
    }

    @Override
    public int compareOrderTo(Parameter<?> value) {
        if (value instanceof MandatoryParameter) {
            return this.index - ((MandatoryParameter<T>) value).index;
        }

        return super.compareOrderTo(value);
    }

}
