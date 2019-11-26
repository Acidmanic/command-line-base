package com.acidmanic.commandline.utility;

import java.util.Collection;

import com.acidmanic.commandline.commands.parameters.Parameter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ParameterDescriber  {

    public String describeParameters(Collection<Parameter<?>> params){
        
        Collection<Parameter<?>> sortedParams = sort(params);
        
        StringBuilder sb = new StringBuilder();

        appendCommandCall(sb,sortedParams);

        appendParameterDescriptions(sb,sortedParams);
        
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

                mandatories += param.describeProvision() + " ";

            }else{

                anyOptional = true;
                
                optionals += param.describeProvision() + " ";
            }
        }

        sb.append(mandatories.trim());

        if(anyOptional){

            sb.append(" [ ").append(optionals.trim()).append(" ]");
        }
        
        return sb;
    }

    private Collection<Parameter<?>> sort(Collection<Parameter<?>> params) {
        
        List<Parameter<?>> sortedParams = new ArrayList<>();
        
        params.forEach(param -> sortedParams.add(param));
        
        sortedParams.sort((o1,o2) -> compare (o1,o2));
        
        return sortedParams;
    }

    private int compare(Parameter<?> o1, Parameter<?> o2) {
        if(o1.isMandatory() && !o2.isMandatory()){
            return 10;
        }
        
        if(!o1.isMandatory() && o2.isMandatory()){
            return -10;
        }
        
        return o1.compareOrderTo(o2);
    }
    
    
}