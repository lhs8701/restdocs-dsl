package hansol.restdocsdsl.docs;

import hansol.restdocsdsl.element.FieldElement;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.snippet.Attributes;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class RestDocsRequest extends DocsRoot{
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
            if (member.getOptional() != null){
                fieldDescriptor.optional();
            }
            if (member.getDescription() != null){
                fieldDescriptor.description(member.getDescription());
            }
            if (member.getType() != null){
                fieldDescriptor.type(member.getType());
            }
            fieldDescriptors.add(fieldDescriptor);
        }
        return PayloadDocumentation.requestFields(fieldDescriptors);
    }
}
