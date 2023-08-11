# Restdocs DSL
RestDocs를 DSL화하여 사용하기 쉽도록 만들었습니다.

<br>

## Dependency
### Gradle
```groovy
implementation 'io.github.lhs8701:restdocs-dslized-library:1.2.1'
```
### Maven
```xml
<dependency>
  <groupId>io.github.lhs8701</groupId>
  <artifactId>restdocs-dslized-library</artifactId>
  <version>1.2.0</version>
</dependency>
```

<br>

## 주요 특징
- **Auto Pretty Print** : 예시의 JSON 데이터를 가시적으로 보여줍니다. 
- **Type Attribute** : 필드의 타입을 명시할 수 있는 메서드를 지원합니다.  
- **Default Value Attribute** : 필드의 기본 값을 명시할 수 있는 메서드를 지원합니다.  
- **Constraints Attribute** : 필드의 제약 조건을 명시할 수 있는 메서드를 지원합니다.  
- **Simple Format** : 작성해야 하는 코드 양이 적고, 쉽게 사용할 수 있습니다.  

<br>

## 사용 예시
> 자세한 사용법은 Wiki를 참조해주세요

### Header
```java
import static hansol.restdocsdsl.docs.RestDocsAdapter.docs;
import static hansol.restdocsdsl.docs.RestDocsHeader.requestHeaders;
import static hansol.restdocsdsl.element.HeaderElement.header;

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
```

### Request Field
```java
import static hansol.restdocsdsl.docs.RestDocsAdapter.docs;
import static hansol.restdocsdsl.docs.RestDocsRequest.requestFields;
import static hansol.restdocsdsl.element.FieldElement.field;

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
                             field("nickname").type(JsonFieldType.STRING).description("이름").defaultValue("익명의 사용자"),
                             field("address").description("주소").type(JsonFieldType.STRING).optional().constraints("도로명 주소 형식")
                     )));
 }
```

### Response Field
```java
import static hansol.restdocsdsl.docs.RestDocsAdapter.docs;
import static hansol.restdocsdsl.docs.RestDocsResponse.responseFields;
import static hansol.restdocsdsl.element.FieldElement.field;

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
```

### Path Parameter
```java
import static hansol.restdocsdsl.docs.RestDocsAdapter.docs;
import static hansol.restdocsdsl.docs.RestDocsPathParam.pathParams;
import static hansol.restdocsdsl.element.ParamElement.param;

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
```

### Query Parameter
```java
import static hansol.restdocsdsl.docs.RestDocsAdapter.docs;
import static hansol.restdocsdsl.docs.RestDocsQueryParam.queryParams;
import static hansol.restdocsdsl.element.ParamElement.param;

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
```

### Request Part
```java
import static hansol.restdocsdsl.docs.RestDocsAdapter.docs;
import static hansol.restdocsdsl.docs.RestDocsRequestPart.requestParts;
import static hansol.restdocsdsl.docs.RestDocsRequestPartField.requestPartFields;
import static hansol.restdocsdsl.element.PartElement.part;
import static hansol.restdocsdsl.element.FieldElement.field;

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
```
