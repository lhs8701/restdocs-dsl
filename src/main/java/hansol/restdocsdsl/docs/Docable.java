package hansol.restdocsdsl.docs;

import org.springframework.restdocs.snippet.Snippet;

public interface Docable {
    Snippet toSnippets();
}
