package com.example.tobyboot;

import com.example.tobyboot.toby.HelloController;
import com.example.tobyboot.toby.SimpleHelloService;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class TobybootApplication {

    public static void main(String[] args) {
        // spring container 만드는 작업
        // 익명 클래스
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() {
            @Override
            protected void onRefresh() {
                // 생략 하면 안됨.
                super.onRefresh();
                // 서블릿 컨테이너를 초기화하는 코드
                TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                // servlet container를 코드로 실행하면서 servlet을 등록하는 작업
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet",
                            new DispatcherServlet(this)
                    ).addMapping("/*");
                });
                // 톰캣 서블릿 컨테이너가 동작한다.
                webServer.start();
            }
        };
        // bean 등록
        applicationContext.registerBean(HelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        // 초기화 작업
        applicationContext.refresh();

        System.out.println("Hello containerless Standalone Application");
    }
}
