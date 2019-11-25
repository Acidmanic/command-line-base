package com.acidmanic.commandline.utility;

import java.util.Collection;

import com.acidmanic.commandline.commands.parameters.Parameter;

public class ParameterDescriber  {

    public String describeParameters(Collection<Parameter<?>> params){
        StringBuilder sb = new StringBuilder();

        appendCommandCall(sb,params);

        appendParameterDescriptions(sb,params);
        
        return sb.toString();
    }

    private void appendParameterDescriptions(StringBuilder sb, Collection<Parameter<?>> params) {
        
        for(Parameter<?> param : params){

            String name = param.getName();

            sb.append('\n').append('\t').append(name).append('\t')

            .append(param.getDescription());
        }
    }

    private StringBuilder appendCommandCall(StringBuilder sb, Collection<Parameter<?>> params) {

        String mandatories = "";
        String optionals ="";
        boolean anyOptional = false;

        for(Parameter<?> param : params){

            if(param.isMandatory()){

                mandatories += param.describeProvision();

            }else{

                anyOptional = true;
                
                optionals += param.describeProvision();
            }
        }

        sb.append(mandatories);

        if(anyOptional){

            sb.append(" [").append(optionals).append(" ]");
        }
        
        return sb;
    }
}