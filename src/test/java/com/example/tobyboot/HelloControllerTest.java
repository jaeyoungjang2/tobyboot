package com.example.tobyboot;

import com.example.tobyboot.toby.HelloController;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class HelloControllerTest {

    // 정상 케이스 테스트
    @Test
    void helloController() {
        HelloController helloController = new HelloController(name -> name);

        String ret = helloController.hello("Test");

        assertThat(ret).isEqualTo("Test");
    }

    // 비정상도 테스트 가정
    @Test
    void failsHelloController() {
        HelloController helloController = new HelloController(name -> name);

        assertThatThrownBy(() -> {
            helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            helloController.hello("");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
