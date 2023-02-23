package com.example.tobyboot;

import com.example.tobyboot.toby.HelloDecorator;
import com.example.tobyboot.toby.SimpleHelloService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@UnitTest
@interface FastUnitTest {

}

@Retention(RetentionPolicy.RUNTIME)
// annotation type으로 지정을 하였기 때문에 위에 meta annotation을 만들 때 또 사용할 수 있다.
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Test
@interface UnitTest {

}

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
