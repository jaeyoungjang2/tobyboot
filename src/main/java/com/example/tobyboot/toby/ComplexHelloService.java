package com.example.tobyboot.toby;

public class ComplexHelloService implements HelloService {
    @Override
    public String sayHello(String name) {
        return "bye " + name;
    }
}
