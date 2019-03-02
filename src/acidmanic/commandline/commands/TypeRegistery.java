/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acidmanic.commandline.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class TypeRegistery implements ITypeRegistery {
    
    protected final ArrayList<String> allCommandClasses;
    protected final HashSet<String> commandsExistance;

    public TypeRegistery() {
        allCommandClasses = new ArrayList<>();
        commandsExistance = new HashSet<>();
        registerClass(Help.class);
        registerClass(Version.class);
    }

    @Override
    public final void registerClass(Class refType) {
        registerClass(refType.getName());
    }

    @Override
    public void registerClass(String fullName) {
        if (commandsExistance.contains(fullName) == false) {
            commandsExistance.add(fullName);
            allCommandClasses.add(fullName);
        }
    }

    protected void putAllNames() {
        registerClass(Command.class.getName());
        registerClass(CommandBase.class.getName());
        registerClass(BatchCommandBase.class.getName());
        registerClass(Help.class.getName());
    }

    protected Class[] getAllInterfacesEver(Class cls) {
        ArrayList<Class> ret = new ArrayList<>();
        Class sup = cls;
        while (sup != null && !sup.equals(Object.class)) {
            Class[] interfaces = sup.getInterfaces();
            ret.addAll(Arrays.asList(interfaces));
            sup = sup.getSuperclass();
        }
        return ret.toArray(new Class[]{});
    }

    @Override
    public boolean hasImplemented(Class subClass, Class superClass) {
        Class[] allInts = getAllInterfacesEver(subClass);
        for (Class c : allInts) {
            if (c.equals(superClass)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isOfType(Class subClass, Class superClass) {
        if (superClass.isInterface()) {
            return hasImplemented(subClass, superClass);
        }
        Class sup = subClass;
        while ((sup = sup.getSuperclass()) != null) {
            if (sup.equals(superClass)) {
                return true;
            }
            if (sup.equals(Object.class)) {
                return false;
            }
        }
        return false;
    }

    protected ArrayList<String> getClassNames(ArrayList<String> entries, String packageName) {
        ArrayList<String> ret = new ArrayList<>();
        for (String ent : entries) {
            if (ent.startsWith(packageName)) {
                ret.add(ent);
            }
        }
        return ret;
    }

    @Override
    public ArrayList<String> getApplicationClassesNames() {
        return allCommandClasses;
    }

    @Override
    public ArrayList<Class> getApplicationClasses() {
        ArrayList<Class> ret = new ArrayList<>();
        ArrayList<String> names = getApplicationClassesNames();
        for (String cname : names) {
            try {
                Class c = Class.forName(cname);
                ret.add(c);
            } catch (Exception ex) {
            }
        }
        return ret;
    }

    @Override
    public ArrayList<Class> getClasses(Class superClass) {
        ArrayList<Class> ret = new ArrayList<>();
        ArrayList<String> names = getApplicationClassesNames();
        for (String cname : names) {
            try {
                Class c = Class.forName(cname);
                if (isOfType(c, superClass)) {
                    ret.add(c);
                }
            } catch (Exception ex) {
            }
        }
        return ret;
    }
    
}
