package hansol.restdocsdsl.element;

public class ParamElement {
    private final String name;
    private String description;
    private Boolean optional;


    public ParamElement(String name) {
        this.name = name;
    }

    public static ParamElement param(String name){
        return new ParamElement(name);
    }

    public ParamElement description(String description) {
        this.description = description;
        return this;
    }

    public ParamElement optional() {
        this.optional = Boolean.TRUE;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getOptional() {
        return optional;
    }
}
