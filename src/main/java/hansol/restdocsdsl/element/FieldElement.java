package hansol.restdocsdsl.element;

import org.springframework.restdocs.payload.JsonFieldType;

public class FieldElement {
    private final String name;
    private String description;
    private JsonFieldType type;
    private Boolean optional;
    private String defaultValue;
    private String constraints;


    public FieldElement(String name) {
        this.name = name;
    }

    public static FieldElement field(String name){
        return new FieldElement(name);
    }

    public FieldElement description(String description) {
        this.description = description;
        return this;
    }

    public FieldElement type(JsonFieldType type) {
        this.type = type;
        return this;
    }

    public FieldElement optional() {
        this.optional = Boolean.TRUE;
        return this;
    }

    public FieldElement defaultValue(String value) {
        this.defaultValue = value;
        return this;
    }

    public FieldElement constraints(String constraints) {
        this.constraints = constraints;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public JsonFieldType getType() {
        return type;
    }

    public Boolean getOptional() {
        return optional;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String getConstraints() {
        return constraints;
    }
}
