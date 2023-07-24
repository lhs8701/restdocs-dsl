package hansol.restdocsdsl.docs;

import hansol.restdocsdsl.element.FieldElement;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.payload.RequestPartFieldsSnippet;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class RestDocsRequestPartField extends DocsRoot {
    private final String partName;
    private final FieldElement[] fieldElements;

    public RestDocsRequestPartField(String partName, FieldElement[] fieldElements) {
        this.partName = partName;
        this.fieldElements = fieldElements;
    }

    public static RestDocsRequestPartField requestPartFields(String partName, FieldElement... fieldElements) {
        return new RestDocsRequestPartField(partName, fieldElements);
    }

    public RequestPartFieldsSnippet toSnippets() {
        List<FieldDescriptor> fieldDescriptors = new ArrayList<>();
        for (FieldElement member : fieldElements) {
            FieldDescriptor fieldDescriptor = fieldWithPath(member.getName());
            if (member.getOptional() != null) {
                fieldDescriptor.optional();
            }
            if (member.getDescription() != null) {
                fieldDescriptor.description(member.getDescription());
            }
            fieldDescriptors.add(fieldDescriptor);
        }
        return PayloadDocumentation.requestPartFields(partName, fieldDescriptors);
    }
}
