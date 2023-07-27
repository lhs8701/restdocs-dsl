package hansol.restdocsdsl.docs;

import hansol.restdocsdsl.element.ParamElement;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.restdocs.request.QueryParametersSnippet;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;

public class RestDocsQueryParam implements Docable {
    private final ParamElement[] paramElements;

    public RestDocsQueryParam(ParamElement[] paramElements) {
        this.paramElements = paramElements;
    }

    public static RestDocsQueryParam queryParams(ParamElement... paramElements) {
        return new RestDocsQueryParam(paramElements);
    }

    public QueryParametersSnippet toSnippets() {
        List<ParameterDescriptor> parameterDescriptors = new ArrayList<>();
        for (ParamElement member : paramElements) {
            ParameterDescriptor parameterDescriptor = parameterWithName(member.getName());
            if (member.getOptional() != null){
                parameterDescriptor.optional();
            }
            if (member.getDescription() != null){
                parameterDescriptor.description(member.getDescription());
            }
            parameterDescriptors.add(parameterDescriptor);
        }
        return queryParameters(parameterDescriptors);
    }
}
