package com.acidmanic.commandline.argumentparsing;



public class ArgumentProperties {


    private String name;
    private int index;



    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ArgumentProperties(String name) {
        this.name = name;
    }

    public ArgumentProperties(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public ArgumentProperties() {
        super();
    }

}