/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.commandline.commands;

import com.acidmanic.commandline.commandnames.NameGeneratorBuilder;

import java.io.File;
import java.security.CodeSource;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class Version extends CommandBase{

    private final static String ATTR_TAG_START="Application-";

    public Version() {
        this.setNameGenerator(NameGeneratorBuilder.makeDoubleDashedSnakecaseClassnameNameGenerator(Version.class));
    }

    @Override
    protected String getUsageString() {
        return "Prints the application version";
    }

    @Override
    public void execute() {
        
        StackTraceElement trace[] = Thread.currentThread().getStackTrace();
        if (trace.length > 0) {
            String className = trace[trace.length - 1].getClassName();
         try {
                Class main = Class.forName(className);
                CodeSource codeSource = main.getProtectionDomain().getCodeSource();
                File jarFile = new File(codeSource.getLocation().toURI().getPath());
                
                JarFile jar = new JarFile(jarFile);
                
                Manifest manifest = jar.getManifest();
                
                Attributes atts =  manifest.getMainAttributes();
                
                Package pack = main.getPackage();
                
                String title = pack.getImplementationTitle();
                String vendor = pack.getImplementationVendor();
                String version = pack.getImplementationVersion();
                
                if(notEmpty(title) && notEmpty(vendor)){
                    log(vendor + " - " + title);
                }
                
                if(notEmpty(version)){
                    log("Version: " + version);
                }
                
                for(Object key:atts.keySet()){
                    if(key instanceof Attributes.Name){
                        Attributes.Name name = (Attributes.Name) key;
                        String nameKey = name.toString();
                        if(nameKey.startsWith(ATTR_TAG_START)){
                            log((String)atts.get(key));
                        }
                        
                    }
                    
                }
                
            } catch (Exception ex) {
                warning("Version information is not provided within the application.");
            }
        }
    }

    private boolean notEmpty(String value) {
        return (value!=null&&value.length()>0);
    }
    
}
