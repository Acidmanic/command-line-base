package com.acidmanic.commandline.utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Convert{



    @SuppressWarnings("unchecked")
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
                return (T) tryParse(svalue);
            }
            if(type.equals(File.class)){
                return (T) new File(svalue);
            }
            
        } catch (Exception e) {        }
        

        return null;
    }


    private static final String[] DATE_FORMATS = {"yyyy/MM/dd","yyyy\\MM\\dd","yyyy-MM-dd","yyyy MM dd","yyyyMMdd"
                                                ,"yyyy/MM/dd hh:mm","yyyy\\MM\\dd hh:mm","yyyy-MM-dd hh:mm","yyyy MM dd hh:mm","yyyyMMddhhmm"
                                                ,"yyyy/MM/dd hh:mm:ss","yyyy\\MM\\dd hh:mm:ss","yyyy-MM-dd hh:mm:ss","yyyy MM dd hh:mm:ss","yyyyMMddhhmmss"};


    public static Date tryParse(String sdate){


        try {
            return new Date(sdate);
        } catch (Exception e) {}

        for(String pattern : DATE_FORMATS){

            SimpleDateFormat format = new SimpleDateFormat(pattern);

            try {
                 return format.parse(sdate);
            } catch (Exception e) {}

        }

        return null;
        
    }


}