package acidmanic.commandline.commands;

public interface Parameter<T> {




    void parse(String[] args);

    String getDescription();

    T getValue();

    boolean hasValue();


}