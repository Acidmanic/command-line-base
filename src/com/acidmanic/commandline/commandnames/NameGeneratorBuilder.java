package com.acidmanic.commandline.commandnames;



public class NameGeneratorBuilder  {


    public static NameGenerator makeDoubleDashedSnakecaseClassnameNameGenerator(Class<?> type){
        return new DoubleDashedNameGenerator(
            new SnakeCaseNameGenerator(
                new ClassNameNameGenerator(type)
            )
        );
    }
}