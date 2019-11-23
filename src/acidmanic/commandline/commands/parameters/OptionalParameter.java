package acidmanic.commandline.commands.parameters;

import acidmanic.commandline.utility.PrimaryConvertor;

public class OptionalParameter<T> extends ParameterBase<T> {

    public OptionalParameter(String name, Class<T> type) {
        super(name, type);
    }

    @Override
    public void parse(String[] args) {

        for(String arg:args){
            if(arg.toLowerCase().startsWith(this.getName().toLowerCase()+"=")){
                String svalue = arg.substring(this.getName().length()+1,arg.length());

                T value = PrimaryConvertor.convert(svalue, this.getType());

                if(value != null){
                    this.setValue(value);
                }
            }
        }
    }

}