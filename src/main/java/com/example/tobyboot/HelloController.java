package com.example.tobyboot;

import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class HelloController {

    public String hello(String name) {
        HelloService helloService = new HelloService();

        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
