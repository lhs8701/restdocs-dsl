package hansol.restdocsdsl.element;

public class HeaderElement {
    private final String name;
    private String description;
    private boolean optional;

    public HeaderElement(String name) {
        this.name = name;
    }

    public static HeaderElement header(String name){
        return new HeaderElement(name);
    }

    public HeaderElement description(String description) {
        this.description = description;
        return this;
    }

    public HeaderElement optional() {
        this.optional = true;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isOptional() {
        return optional;
    }
}
