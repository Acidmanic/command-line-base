/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acidmanic.commandline.commands;

/**
 *
 * @author diego
 */
public class ApplicationWideTypeRegistery extends TypeRegistery {


    private static ApplicationWideTypeRegistery instance;

    private ApplicationWideTypeRegistery() {
    }


    public static synchronized ApplicationWideTypeRegistery makeInstance() {
        if (instance == null) {
            instance = new ApplicationWideTypeRegistery();
            instance.putAllNames();
        }
        return instance;
    }

}
