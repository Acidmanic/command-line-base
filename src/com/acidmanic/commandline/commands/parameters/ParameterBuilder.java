package com.acidmanic.commandline.commands.parameters;

import java.util.ArrayList;
import java.util.List;

import com.acidmanic.commandline.commandnames.DashedNameGenerator;
import com.acidmanic.commandline.commandnames.DoubleDashedNameGenerator;
import com.acidmanic.commandline.commandnames.FixedStringNameGenerator;
import com.acidmanic.commandline.commandnames.NameGenerator;
import com.acidmanic.commandline.commandnames.SnakeCaseNameGenerator;
import com.acidmanic.commandline.utility.Convert;

public class ParameterBuilder {



    public static final int NAMEGENERATOR_SIMPLE =0;
    public static final int NAMEGENERATOR_SNAKECASE =1;
    public static final int NAMEGENERATOR_DASHED_SNAKECASE =2;
    public static final int NAMEGENERATOR_DOUBLE_DASHED_SNAKECASE =3;

    private String name;
    private String description;
    private ParameterValueFactory factory;
    private boolean isMandatory;
    private int index;
    private boolean touched ;
    private int namegeneratorType=NAMEGENERATOR_SIMPLE;
    

    private List<Parameter<?>> params = new ArrayList<>();

    public ParameterBuilder() {
        this.clear();
        this.params = new ArrayList<>();
    }

    public ParameterBuilder named(String name){

        this.name = name;
        this.touched = true;
        return this;
    }


    public ParameterBuilder described(String description){

        this.description = description;
        this.touched = true;
        return this;
    }

    /***
     *  Will determine the the data type of the parameter.
     *  
     * @param type, Available types are primary types plus {@link java.io.File}, and {@link java.util.Date}
     * @return
     */
    public ParameterBuilder ofType(Class<?> type){

        this.factory =  sValue -> Convert.convert(sValue, type);
        this.touched = true;
        return this;
    }
    
    
    public ParameterBuilder withValueFactory(ParameterValueFactory factory){
        this.factory = factory;
        
        this.touched = true;
        
        return this;
    }

    
    public ParameterBuilder mandatory(){

        this.isMandatory = true;
        this.touched = true;
        return this;
    }

    public ParameterBuilder optional(){

        this.isMandatory = false;
        this.touched = true;
        return this;
    }

    public ParameterBuilder indexAt(int index){

        this.index = index;
        this.touched = true;
        return this;
    }

    private <T> Parameter<T> getCurrent(){
        
        ParameterBase<T> ret;

        if(this.isMandatory){
            ret = new MandatoryParameter<T>(this.name, this.index,(Class<T>)this.type);
        }else{
            ret= new OptionalParameter<T>(this.name, (Class<T>)this.type);
        }

        ret.setDescription(this.description);

        NameGenerator gen = getNameGenerator();

        ret.setNameGenerator(gen);

        return ret;
    }


    private NameGenerator getNameGenerator() {
        NameGenerator gen = new FixedStringNameGenerator(this.name);

        if(this.namegeneratorType == NAMEGENERATOR_SNAKECASE){
            return new SnakeCaseNameGenerator(gen);
        }

        if(this.namegeneratorType == NAMEGENERATOR_DASHED_SNAKECASE){
            return new DashedNameGenerator(new SnakeCaseNameGenerator(gen));
        }

        if(this.namegeneratorType == NAMEGENERATOR_DOUBLE_DASHED_SNAKECASE){
            return new DoubleDashedNameGenerator(new SnakeCaseNameGenerator(gen));
        }

        return gen;
    }

    public <T> ParameterBuilder newParam() {

        if(this.touched){
            Parameter<T> current = getCurrent();

            this.params.add(current);

            clear();
        }
        return this;
    }

    private void clear() {
        this.description = "";
        this.index =0;
        this.isMandatory = false;
        this.name = "";
        this.type = String.class;
        this.touched = false;
    }

    public Parameter<?>[] build() {
        

        if(this.touched){
            newParam();

        }

        Parameter<?>[] ret = new Parameter<?>[this.params.size()];
        
        this.params.toArray(ret);

        return ret;
        
    }
}