package com.acidmanic.commandline.commandnames;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class NameGeneratorBuilder {

    private Class seednameGenerator = ClassNameNameGenerator.class;
    private final List<Class> composits = new ArrayList<>();

    public NameGeneratorBuilder seed(Class seedNameGenerator) {

        this.seednameGenerator = seedNameGenerator;

        return this;
    }

    public NameGeneratorBuilder wrapedIn(Class outerComposite) {
        composits.add(outerComposite);

        return this;
    }

    public NameGenerator build(Class commandType, String commandName) {

        NameGenerator nameGenerator = instanciateSeed(commandType, commandName);

        for (Class compositeType : this.composits) {

            nameGenerator = instanciateComposite(compositeType, nameGenerator);
        }
        return nameGenerator;
    }

    private NameGenerator instanciateSeed(Class commandType, String commandName) {
        if (this.seednameGenerator.equals(ClassNameNameGenerator.class)) {
            return new ClassNameNameGenerator(commandType);
        }
        return new FixedStringNameGenerator(commandName);
    }

    private NameGenerator instanciateComposite(Class compositeType, NameGenerator nameGenerator) {

        try {
            Constructor constructor = compositeType.getConstructor(NameGenerator.class);

            constructor.newInstance(nameGenerator);
        } catch (Exception e) {

        }

        return nameGenerator;
    }
}
