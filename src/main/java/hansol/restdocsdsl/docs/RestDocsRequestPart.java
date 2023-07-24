package hansol.restdocsdsl.docs;

import hansol.restdocsdsl.element.PartElement;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.restdocs.request.RequestPartDescriptor;
import org.springframework.restdocs.request.RequestPartsSnippet;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.request.RequestDocumentation.partWithName;

public class RestDocsRequestPart extends DocsRoot{
    private final PartElement[] partElements;

    public RestDocsRequestPart(PartElement[] partElements) {
        this.partElements = partElements;
    }

    public static RestDocsRequestPart requestParts(PartElement... partElements) {
        return new RestDocsRequestPart(partElements);
    }
    public RequestPartsSnippet toSnippets() {
        List<RequestPartDescriptor> partDescriptors = new ArrayList<>();
        for (PartElement member : partElements) {
            RequestPartDescriptor partDescriptor = partWithName(member.getName());
            if (member.getOptional() != null){
                partDescriptor.optional();
            }
            if (member.getDescription() != null){
                partDescriptor.description(member.getDescription());
            }
            partDescriptors.add(partDescriptor);
        }
        return RequestDocumentation.requestParts(partDescriptors);
    }
}
