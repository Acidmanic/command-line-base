package com.acidmanic.commandline.commands.parameters;

import com.acidmanic.commandline.commandnames.NameGenerator;
import com.acidmanic.commandline.utility.PrimaryConvertor;
import com.acidmanic.commandline.argumentparsing.ArgumentProperties;
import com.acidmanic.commandline.argumentparsing.ArgumentReadingStrategy;
import com.acidmanic.commandline.commandnames.FixedStringNameGenerator;

public abstract class ParameterBase<T> implements Parameter<T>{


    private T value;
    private boolean hasValue;
    private String description;
    private Class<T> type;
    private NameGenerator nameGenerator;

    @Override
    public String getName() {
        return this.nameGenerator.generateName();
    }

    public void setName(String name) {
        this.nameGenerator = new FixedStringNameGenerator(name);
    }


    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public boolean hasValue() {
        return this.hasValue;
    }

    protected void setValue(T value){
        
        this.value = value;

        this.hasValue = true;
    }

    public ParameterBase(String name,Class<T> type) {
        this.hasValue = false;
        this.value = null;
        this.type = type;
        this.nameGenerator = new FixedStringNameGenerator(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    public NameGenerator getNameGenerator() {
        return nameGenerator;
    }

    public void setNameGenerator(NameGenerator nameGenerator) {
        this.nameGenerator = nameGenerator;
    }

    protected abstract ArgumentReadingStrategy getReadingStrategy();
    protected abstract ArgumentProperties getArgumentProperties();

    @Override
    public void parse(String[] args) {


        try {

            String svalue = getReadingStrategy().read(getArgumentProperties(),args);

            T value = PrimaryConvertor.convert(svalue, this.getType());

            if(value != null){
                this.setValue(value);
            }
            
        } catch (Exception e) {}
    }
}