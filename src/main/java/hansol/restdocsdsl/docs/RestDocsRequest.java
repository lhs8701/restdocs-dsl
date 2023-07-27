package hansol.restdocsdsl.docs;

import hansol.restdocsdsl.element.FieldElement;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.snippet.Attributes;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class RestDocsRequest implements Docable {
    private final FieldElement[] fieldElements;

    public RestDocsRequest(FieldElement[] fieldElements) {
        this.fieldElements = fieldElements;
    }

    public static RestDocsRequest requestFields(FieldElement... fieldElements) {
        return new RestDocsRequest(fieldElements);
    }
    public RequestFieldsSnippet toSnippets() {
        List<FieldDescriptor> fieldDescriptors = new ArrayList<>();
        for (FieldElement member : fieldElements) {
            FieldDescriptor fieldDescriptor = fieldWithPath(member.getName());
            fieldDescriptor.type(member.getType() == null ? JsonFieldType.STRING : member.getType());
            if (member.getOptional() != null){
                fieldDescriptor.optional();
            }
            if (member.getDescription() != null){
                fieldDescriptor.description(member.getDescription());
            }
            if (member.getDefaultValue() != null) {
                fieldDescriptor.attributes(new Attributes.Attribute("defaultValue", member.getDefaultValue()));
            }
            if (member.getConstraints() != null) {
                fieldDescriptor.attributes(new Attributes.Attribute("constraints", member.getConstraints()));
            }
            fieldDescriptors.add(fieldDescriptor);
        }
        return PayloadDocumentation.requestFields(fieldDescriptors);
    }
}
