package acidmanic.commandline.commands.parameters;

import java.util.ArrayList;
import java.util.List;

public class ParameterBuilder {


    private String name;
    private String description;
    private Class<?> type;
    private boolean isMandatory;
    private int index;
    private boolean touched ;


    private List<Parameter<?>> params = new ArrayList<>();

    public ParameterBuilder() {
        this.clear();
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

    public ParameterBuilder ofType(Class<?> type){

        this.type = type;
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

        return ret;
    }


    public <T> ParameterBuilder newParam(){

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
        this.params = new ArrayList<>();
        this.type = String.class;
        this.touched = false;
    }

    public Parameter<?>[] build() {
        

        if(this.touched){
            newParam();

            this.clear();
        }

        Parameter<?>[] ret = new Parameter<?>[this.params.size()];
        
        this.params.toArray(ret);

        return ret;
        
    }
}