package acidmanic.commandline.commands.parameters;

import java.util.ArrayList;
import java.util.List;

public class ParameterBuilder {


    private String name;
    private String description;
    private Class<?> type;
    private boolean isMandatory;
    private int index;

    private List<Parameter<?>> params = new ArrayList<>();

    public ParameterBuilder named(String name){

        this.name = name;

        return this;
    }


    public ParameterBuilder described(String description){

        this.description = description;

        return this;
    }

    public ParameterBuilder ofType(Class<?> type){

        this.type = type;

        return this;
    }

    
    public ParameterBuilder mandatory(){

        this.isMandatory = true;

        return this;
    }

    public ParameterBuilder optional(){

        this.isMandatory = false;

        return this;
    }

    public ParameterBuilder indexAt(int index){

        this.index = index;

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

        return ret;
    }


    public <T> ParameterBuilder newParam(int index){

        Parameter<T> current = getCurrent();

        this.params.add(current);

        return this;
    }

    public Parameter<?>[] build(){
        
        Parameter<?>[] ret = new Parameter<?>[this.params.size()];
        
        this.params.toArray(ret);

        return ret;
        
    }
}