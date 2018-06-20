/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acidmanic.commandline.application;

/**
 *
 * @author diego
 */
public class Console {
    public static void WriteLine(String text){
        System.out.println(text);
    }
    public static void Write(String text){
        System.out.print(text);
    }
    public static void WriteLine(){
        System.out.println();
    }
}
