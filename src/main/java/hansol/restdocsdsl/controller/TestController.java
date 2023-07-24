package hansol.restdocsdsl.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TestController {

    @PostMapping("/api/header")
    public ResponseEntity<Void> headerApi() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/request-field")
    public ResponseEntity<Void> requestApi(@RequestBody RequestDto requestDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/response-field")
    public ResponseEntity<ResponseDto> responseApi() {
        ResponseDto responseDto = new ResponseDto("19990311");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/api/path-parameter/{id}")
    public ResponseEntity<Void> pathParameterApi(@PathVariable String id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/query-parameter")
    public ResponseEntity<Void> queryParameterApi(@RequestParam String age) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/request-part")
    public ResponseEntity<Void> requestPartApi(@RequestPart MultipartFile file, @RequestPart RequestDto dto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public static class RequestDto {
        String nickname;
        String address;

        public RequestDto() {
        }

        public RequestDto(String nickname, String address) {
            this.nickname = nickname;
            this.address = address;
        }

        public String getNickname() {
            return nickname;
        }
        public String getAddress() {
            return address;
        }
    }

    public static class ResponseDto {
        String birth;

        public ResponseDto(String birth) {
            this.birth = birth;
        }

        public String getBirth() {
            return birth;
        }
    }


}
