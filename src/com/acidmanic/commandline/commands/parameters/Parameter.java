package com.acidmanic.commandline.commands.parameters;

public interface Parameter<T> {




    void parse(String[] args);

    String getDescription();

    String getName();

    T getValue();

    boolean hasValue();

    boolean isMandatory();

}