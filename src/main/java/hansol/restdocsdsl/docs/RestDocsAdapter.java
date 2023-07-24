package hansol.restdocsdsl.docs;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.snippet.Snippet;

import static hansol.restdocsdsl.docs.ApiDocumentUtils.documentRequest;
import static hansol.restdocsdsl.docs.ApiDocumentUtils.documentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;


public class RestDocsAdapter {
    public static RestDocumentationResultHandler docs(String name, DocsRoot... docsRoots) {
        return document(name,
                documentRequest(),
                documentResponse(),
                castToSnippet(docsRoots)
        );
    }

    static Snippet[] castToSnippet(DocsRoot... docsRoots) {
        Snippet[] snippets = new Snippet[docsRoots.length];

        for (int i = 0; i < docsRoots.length; i++) {
            DocsRoot elem = docsRoots[i];
            if (elem instanceof RestDocsHeader) {
                Snippet snippet = ((RestDocsHeader) elem).toSnippets();
                snippets[i] = snippet;
            }
            else if (elem instanceof RestDocsPathParam) {
                Snippet snippet = ((RestDocsPathParam) elem).toSnippets();
                snippets[i] = snippet;
            }
            else if (elem instanceof RestDocsQueryParam) {
                Snippet snippet = ((RestDocsQueryParam) elem).toSnippets();
                snippets[i] = snippet;
            }
            else if (elem instanceof RestDocsRequest) {
                Snippet snippet = ((RestDocsRequest) elem).toSnippets();
                snippets[i] = snippet;
            }
            else if (elem instanceof RestDocsResponse) {
                Snippet snippet = ((RestDocsResponse) elem).toSnippets();
                snippets[i] = snippet;
            }
            else if (elem instanceof RestDocsRequestPart) {
                Snippet snippet = ((RestDocsRequestPart) elem).toSnippets();
                snippets[i] = snippet;
            }
            else if (elem instanceof RestDocsRequestPartField) {
                Snippet snippet = ((RestDocsRequestPartField) elem).toSnippets();
                snippets[i] = snippet;
            }
            else{
                throw new RuntimeException("잘못된 RestDocs DSL 문법입니다.");
            }
        }

        return snippets;
    }

}
