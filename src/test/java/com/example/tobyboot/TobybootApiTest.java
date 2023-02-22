package com.example.tobyboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class TobybootApiTest {
    @Test
    void helloApi() {
        // http localhost:8080/hello?name=spring
        // HTTPie
        // http 요청을 보낼때는 restTemplate을 사용한다.
        // 그러나
        TestRestTemplate rest = new TestRestTemplate();
        // body 부분이 String으로 되어있다.
        // body 가 json으로 되어있을 때는 dto로 받을 수 있겠다.
        ResponseEntity<String> res = rest.getForEntity("http://localhost:8080/hello?name={name}", String.class, "Spring");

        // status code 200
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        // header (content-type) text/plain
        assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        // body Hello Spring
        assertThat(res.getBody()).isEqualTo("Hello Spring");
    }

    @Test
    void failsHelloApi() {
        TestRestTemplate rest = new TestRestTemplate();
        // body 부분이 String으로 되어있다.
        // body 가 json으로 되어있을 때는 dto로 받을 수 있겠다.
        ResponseEntity<String> res = rest.getForEntity("http://localhost:8080/hello?name=", String.class);

        // 프로그램 버그, 서버의 심각한 상황 -> 500 에러
        // 클라이언트가 값을 잘못 보냈을 때는 400번대 에러를 반납하는게 좋다.
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}