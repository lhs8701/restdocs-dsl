package hansol.restdocsdsl.docs;

import hansol.restdocsdsl.element.HeaderElement;
import org.springframework.restdocs.headers.HeaderDescriptor;
import org.springframework.restdocs.headers.HeaderDocumentation;
import org.springframework.restdocs.headers.RequestHeadersSnippet;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;

public class RestDocsHeader implements Docable {

    private final HeaderElement[] headerElements;

    public RestDocsHeader(HeaderElement[] headerElements) {
        this.headerElements = headerElements;
    }

    public static RestDocsHeader requestHeaders(HeaderElement... headerElements) {
        return new RestDocsHeader(headerElements);
    }

    public RequestHeadersSnippet toSnippets() {
        List<HeaderDescriptor> headerDescriptors = new ArrayList<>();
        for (HeaderElement member : headerElements) {
            HeaderDescriptor headerDescriptor = headerWithName(member.getName());
            if (member.getDescription() != null) {
                headerDescriptor.description(member.getDescription());
            }
            if (member.isOptional() != null) {
                headerDescriptor.optional();
            }
            headerDescriptors.add(headerDescriptor);
        }
        return HeaderDocumentation.requestHeaders(headerDescriptors);
    }

}
