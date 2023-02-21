package com.example.tobyboot.toby;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {
    public static void run(Class<?> applicationClass, String... args) {
        // spring container 만드는 작업
        // 익명 클래스
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                // 생략 하면 안됨.
                super.onRefresh();
                // 서블릿 컨테이너를 초기화하는 코드
                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
                // application context를 주입 (그러나 application context를 주입하지 않아도 스프링 컨테이너가 알아서 주입해준다.)
                // dispatcherServlet.setApplicationContext(this);

                // servlet container를 코드로 실행하면서 servlet을 등록하는 작업
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet",
                                    dispatcherServlet)
                            .addMapping("/*");
                });
                // 톰캣 서블릿 컨테이너가 동작한다.
                webServer.start();
            }
        };
        // bean 등록
        applicationContext.register(applicationClass);
        // 초기화 작업
        applicationContext.refresh();
        System.out.println("Hello containerless Standalone Application");
    }
}
