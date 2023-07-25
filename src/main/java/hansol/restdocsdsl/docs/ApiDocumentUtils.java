package hansol.restdocsdsl.docs;

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

public interface ApiDocumentUtils {

    static OperationRequestPreprocessor documentRequest() {
        return preprocessRequest(
                modifyUris()
                        .scheme("http")
                        .host("localhost")
                        .port(8080),
                prettyPrint());
    }

    static OperationResponsePreprocessor documentResponse() {
        return preprocessResponse(
                modifyHeaders().remove("Vary"),
                modifyHeaders().remove("X-Content-Type-Options"),
                modifyHeaders().remove("X-XSS-Protection"),
                modifyHeaders().remove("Cache-Control"),
                modifyHeaders().remove("Pragma"),
                modifyHeaders().remove("Expires"),
                modifyHeaders().remove("X-Frame-Options"),
                prettyPrint());
    }
}