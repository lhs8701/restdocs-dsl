package hansol.restdocsdsl;

import com.fasterxml.jackson.databind.ObjectMapper;
import hansol.restdocsdsl.controller.TestController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.StandardCharsets;

import static hansol.restdocsdsl.docs.RestDocsAdapter.docs;
import static hansol.restdocsdsl.docs.RestDocsHeader.requestHeaders;
import static hansol.restdocsdsl.docs.RestDocsPathParam.pathParams;
import static hansol.restdocsdsl.docs.RestDocsQueryParam.queryParams;
import static hansol.restdocsdsl.docs.RestDocsRequest.requestFields;
import static hansol.restdocsdsl.docs.RestDocsRequestPart.requestParts;
import static hansol.restdocsdsl.docs.RestDocsRequestPartField.requestPartFields;
import static hansol.restdocsdsl.docs.RestDocsResponse.responseFields;
import static hansol.restdocsdsl.element.FieldElement.field;
import static hansol.restdocsdsl.element.HeaderElement.header;
import static hansol.restdocsdsl.element.ParamElement.param;
import static hansol.restdocsdsl.element.PartElement.part;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class DocsTest {
    @Autowired
    protected MockMvc mvc;

    @Test
    @DisplayName("Header")
    void test1() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(post("/api/header")
                .header("test-header", "value"));

        // then
        actions.andExpect(status().isOk())
                .andDo(docs("header",
                        requestHeaders(
                                header("test-header").description("헤더")
                        )));
    }

    @Test
    @DisplayName("Request Field")
    void test2() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(post("/api/request-field")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(new TestController.RequestDto("홍길동", "서울"))));

        // then
        actions.andExpect(status().isOk())
                .andDo(docs("request-field",
                        requestFields(
                                field("nickname").description("이름"),
                                field("address").description("주소")
                        )));
    }

    @Test
    @DisplayName("Response Field")
    void test3() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(post("/api/response-field"));

        // then
        actions.andExpect(status().isOk())
                .andDo(docs("response-field",
                        responseFields(
                                field("birth").description("생년월일")
                        )));
    }

    @Test
    @DisplayName("Path Parameter")
    void test4() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(post("/api/path-parameter/{id}", "abc"));

        // then
        actions.andExpect(status().isOk())
                .andDo(docs("path-parameter",
                        pathParams(
                                param("id").description("아이디")
                        )));
    }

    @Test
    @DisplayName("Query Parameter")
    void test5() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(post("/api/query-parameter")
                .queryParam("age", "25"));

        // then
        actions.andExpect(status().isOk())
                .andDo(docs("query-parameter",
                        queryParams(
                                param("age").description("나이")
                        )));
    }

    @Test
    @DisplayName("Request Part")
    void test6() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(multipart(HttpMethod.POST, "/api/request-part")
                .file(new MockMultipartFile("file", "image.jpg", MediaType.IMAGE_JPEG_VALUE, "content".getBytes(StandardCharsets.UTF_8)))
                .file(new MockMultipartFile("dto", "objects.json", MediaType.APPLICATION_JSON_VALUE, ("{" + "  \"nickname\": \"홍길동\"," + "  \"address\": \"서울\"" + "}").getBytes(StandardCharsets.UTF_8))));

        // then
        actions.andExpect(status().isOk())
                .andDo(docs("request-part",
                        requestParts(
                                part("file").description("파일"),
                                part("dto").description("JSON 데이터")
                        ),
                        requestPartFields("dto",
                                field("nickname").description("별명"),
                                field("address").description("주소")
                        )));
    }
}
