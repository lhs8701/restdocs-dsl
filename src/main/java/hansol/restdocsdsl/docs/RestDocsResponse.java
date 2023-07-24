package hansol.restdocsdsl.docs;

import hansol.restdocsdsl.element.FieldElement;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class RestDocsResponse extends DocsRoot {
    private final FieldElement[] fieldElements;

    public RestDocsResponse(FieldElement[] fieldElements) {
        this.fieldElements = fieldElements;
    }

    public static RestDocsResponse responseFields(FieldElement... fieldElements) {
        return new RestDocsResponse(fieldElements);
    }

    public ResponseFieldsSnippet toSnippets() {
        List<FieldDescriptor> fieldDescriptors = new ArrayList<>();
        for (FieldElement member : fieldElements) {
            FieldDescriptor fieldDescriptor = fieldWithPath(member.getName());
            if (member.getOptional() != null) {
                fieldDescriptor.optional();
            }
            if (member.getDescription() != null) {
                fieldDescriptor.description(member.getDescription());
            }
            if (member.getType() != null) {
                fieldDescriptor.type(member.getType());
            }
            fieldDescriptors.add(fieldDescriptor);
        }
        return PayloadDocumentation.responseFields(fieldDescriptors);
    }
}
