package com.acidmanic.commandline.commands.parameters;
import com.acidmanic.commandline.commandnames.NameGenerator;
import com.acidmanic.commandline.utility.Convert;
import com.acidmanic.commandline.argumentparsing.ArgumentProperties;
import com.acidmanic.commandline.argumentparsing.ArgumentReadingStrategy;
import com.acidmanic.commandline.commandnames.FixedStringNameGenerator;

public abstract class ParameterBase<T> implements Parameter<T>{


    private T value;
    private boolean hasValue;
    private String description;
    private NameGenerator nameGenerator;
    private ParameterValueFactory<T> factory;
    
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

    public ParameterValueFactory<T> getFactory() {
        return factory;
    }

    public void setFactory(ParameterValueFactory<T> factory) {
        this.factory = factory;
    }
    
    

    public ParameterBase(String name) {
        this.hasValue = false;
        this.value = null;
        this.factory = svalue  ->  (T)svalue;
        this.nameGenerator = new FixedStringNameGenerator(name);
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    public String describeProvision() {
        return getReadingStrategy()
        .describeProvision(
            getArgumentProperties()
        );
    }

    @Override
    public void parse(String[] args) {


        try {
            
            String svalue = getReadingStrategy().read(getArgumentProperties(),args);

            T oValue = this.factory.convert(svalue);

            if(oValue != null){
                this.setValue(oValue);
            }
            
        } catch (Exception e) {}
    }
    
    @Override
    public int compareOrderTo(Parameter<?> value) {
        return this.getName().compareTo(value.getName());
    }
}