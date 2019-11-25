package com.acidmanic.commandline.playground;

import com.acidmanic.commandline.application.ExecutionEnvironment;
import com.acidmanic.commandline.commands.ApplicationWideTypeRegistery;

public class ParamValuesTest {




    public static void main(String[] args) {
        

        ExecutionEnvironment env = new ExecutionEnvironment();

        ApplicationWideTypeRegistery.makeInstance().registerClass(Command3.class);

        env.execute(new String[]{"a-very-large-name-for-a-command","..\\..\\.","pashmak-date","2019-10-02"});
    }
}