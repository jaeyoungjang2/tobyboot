package com.example.tobyboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

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
                    String name = req.getParameter("name");

                    // 응답에 관한 코드
                    // 상태 라인, 상태 코드
                    resp.setStatus(HttpStatus.OK.value());
                    // 컨텐츠 타입 헤더
                    resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                    // 바디
                    resp.getWriter().println("Hello " + name);
                }
            }).addMapping("/hello2");
        });
        // 톰캣 서블릿 컨테이너가 동작한다.
        webServer.start();

        System.out.println("Hello containerless Standalone Application");
    }
}
