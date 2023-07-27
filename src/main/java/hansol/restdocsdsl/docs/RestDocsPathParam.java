package hansol.restdocsdsl.docs;

import hansol.restdocsdsl.element.ParamElement;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.restdocs.request.PathParametersSnippet;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

public class RestDocsPathParam implements Docable {
    private final ParamElement[] paramElements;

    public static RestDocsPathParam pathParams(ParamElement... paramElements) {
        return new RestDocsPathParam(paramElements);
    }

    public RestDocsPathParam(ParamElement[] paramElements) {
        this.paramElements = paramElements;
    }

    public PathParametersSnippet toSnippets() {
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
        return pathParameters(parameterDescriptors);
    }
}
