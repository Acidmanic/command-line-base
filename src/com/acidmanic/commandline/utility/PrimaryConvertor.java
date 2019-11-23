package com.acidmanic.commandline.utility;

import java.util.Date;

public class PrimaryConvertor{



    public static <T> T convert(String svalue,Class<T> type){
        try {
            
            if(type.equals(Integer.class) || type.equals(int.class)){
                return (T) (Integer)Integer.parseInt(svalue);
            }
            if(type.equals(Long.class) || type.equals(long.class)){
                return (T) (Long)Long.parseLong(svalue);
            }
            if(type.equals(Short.class) || type.equals(short.class)){
                return (T) (Short)Short.parseShort(svalue);
            }
            if(type.equals(String.class)){
                return (T) svalue;
            }
            if(type.equals(Byte.class) || type.equals(byte.class)){
                return (T) (Byte)Byte.parseByte(svalue);
            }
            if(type.equals(Boolean.class) || type.equals(boolean.class)){
                return (T) (Boolean)Boolean.parseBoolean(svalue);
            }
            if(type.equals(Character.class) || type.equals(char.class)){
                return (T) (Character)svalue.charAt(0);
            }
            if(type.equals(Date.class)){
                return (T) new Date(svalue);
            }

            
        } catch (Exception e) {        }
        

        return null;
    }
}