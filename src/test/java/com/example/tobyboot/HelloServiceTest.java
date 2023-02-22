package com.example.tobyboot;

import com.example.tobyboot.toby.HelloDecorator;
import com.example.tobyboot.toby.SimpleHelloService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {

    @Test
    void simpleHelloService() {
        SimpleHelloService helloService = new SimpleHelloService();
        String res = helloService.sayHello("Test");

        Assertions.assertThat(res).isEqualTo("Hello Test");
    }

    @Test
    void helloDecorator() {
        HelloDecorator decorator = new HelloDecorator(name -> name);

        String ret = decorator.sayHello("Test");
        Assertions.assertThat(ret).isEqualTo("*Test*");
    }
}
