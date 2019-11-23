package acidmanic.commandline.commands.parameters;


public abstract class ParameterBase<T> implements Parameter<T>{


    private String name;
    private T value;
    private boolean hasValue;
    private String description;
    private Class<T> type;


    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public boolean hasValue() {
        return this.hasValue;
    }

    protected void setValue(T value){
        
        this.value = value;

        this.hasValue = true;
    }

    public ParameterBase(String name,Class<T> type) {
        this.name = name;
        this.hasValue = false;
        this.value = null;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

}