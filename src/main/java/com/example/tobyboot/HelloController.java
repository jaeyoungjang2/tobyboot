package com.example.tobyboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

//    http://localhost:8080/hello?name=string
    @GetMapping("/hello")
    public String hello(String name) {
        return "Hello " + name;
    }
}
