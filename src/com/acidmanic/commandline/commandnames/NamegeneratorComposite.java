package com.acidmanic.commandline.commandnames;




public abstract class NamegeneratorComposite implements NameGenerator {



    private final NameGenerator inner;

    public NamegeneratorComposite(NameGenerator inner) {
        this.inner = inner;
    }

    @Override
    public String generateName() {
        String name = this.inner.generateName();

        name = generateNameBasedOn(name);

        return name;
    }


    protected abstract String generateNameBasedOn(String innerName);


}