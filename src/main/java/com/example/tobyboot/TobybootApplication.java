package com.example.tobyboot;

import com.example.tobyboot.toby.HelloController;
import com.example.tobyboot.toby.HelloService;
import com.example.tobyboot.toby.SimpleHelloService;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class TobybootApplication {
    @Bean
    public HelloController helloController(HelloService helloService) {
        return new HelloController(helloService);
    }

    @Bean
    public HelloService helloService() {
        return new SimpleHelloService();
    }

    public static void main(String[] args) {
        // spring container 만드는 작업
        // 익명 클래스
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                // 생략 하면 안됨.
                super.onRefresh();
                // 서블릿 컨테이너를 초기화하는 코드
                TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                // servlet container를 코드로 실행하면서 servlet을 등록하는 작업
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet",
                            new DispatcherServlet(this))
                    .addMapping("/*");
                });
                // 톰캣 서블릿 컨테이너가 동작한다.
                webServer.start();
            }
        };
        // bean 등록
        applicationContext.register(TobybootApplication.class);
        // 초기화 작업
        applicationContext.refresh();

        System.out.println("Hello containerless Standalone Application");
    }
}
