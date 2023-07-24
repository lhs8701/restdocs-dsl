package hansol.restdocsdsl.docs;

import hansol.restdocsdsl.element.HeaderElement;
import org.springframework.restdocs.headers.HeaderDescriptor;
import org.springframework.restdocs.headers.HeaderDocumentation;
import org.springframework.restdocs.headers.RequestHeadersSnippet;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;

public class RestDocsHeader extends DocsRoot {

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
            headerDescriptors.add(headerWithName(member.getName()).description(member.getDescription()).optional());
        }
        return HeaderDocumentation.requestHeaders(headerDescriptors);
    }

}
