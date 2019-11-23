package acidmanic.commandline.commands.parameters;

public interface Parameter<T> {




    void parse(String[] args);

    String getDescription();

    T getValue();

    boolean hasValue();


}