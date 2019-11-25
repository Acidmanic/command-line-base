package com.acidmanic.commandline.commands.parameters;


public interface Parameter<T> {




    void parse(String[] args);

    String getDescription();

    String getName();

    String describeProvision();

    T getValue();

    boolean hasValue();

    boolean isMandatory();
    
    int compareOrderTo(Parameter<?> value);

}