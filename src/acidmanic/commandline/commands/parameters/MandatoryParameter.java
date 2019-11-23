package acidmanic.commandline.commands.parameters;

import acidmanic.commandline.utility.PrimaryConvertor;

public class MandatoryParameter<T> extends ParameterBase<T>{


    private int index;


    public MandatoryParameter(String name,int index,Class<T> type) {
        super(name,type);

        this.index = index;
         
    }


    @Override
    public void parse(String[] args) {

        if(args.length > this.index){

            T value = PrimaryConvertor.convert(args[this.index], this.getType());

            if(value != null){
                this.setValue(value);
            }
            
        }
    }

   

   

    





}