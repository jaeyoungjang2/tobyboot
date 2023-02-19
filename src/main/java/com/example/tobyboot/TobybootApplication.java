package com.example.tobyboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class TobybootApplication {

    public static void main(String[] args) {
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        // TomcatServletWebServerFactory serverFactory = new JettyServletWebServerFactory();

        // hello로 들어오는 요청은 hello servlet이 처리하도록 한다는 것이다.
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("hello2", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    // 상태 라인, 상태 코드
                    resp.setStatus(200);
                    // 컨텐츠 타입 헤더
                    resp.setHeader("Content-Type", "text/plain");
                    // 바디
                    resp.getWriter().println("Hello Servlet");
                }
            }).addMapping("/hello2");
        });
        // 톰캣 서블릿 컨테이너가 동작한다.
        webServer.start();

        System.out.println("Hello containerless Standalone Application");
    }
}
