package hansol.restdocsdsl.element;

public class PartElement {
    private final String name;
    private String description;
    private Boolean optional;


    public PartElement(String name) {
        this.name = name;
    }

    public static PartElement part(String name){
        return new PartElement(name);
    }

    public PartElement description(String description) {
        this.description = description;
        return this;
    }

    public PartElement optional() {
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
