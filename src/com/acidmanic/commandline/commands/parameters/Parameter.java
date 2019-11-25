package com.acidmanic.commandline.commands.parameters;

import com.acidmanic.commandline.argumentparsing.ArgumentReadingStrategy;

public interface Parameter<T> {




    void parse(String[] args);

    String getDescription();

    String getName();

    String describeProvision();

    T getValue();

    boolean hasValue();

    boolean isMandatory();

}