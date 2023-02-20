package com.example.tobyboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.registerBean(HelloController.class);
        applicationContext.refresh();

        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("frontcontrller", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    // 인증, 보안, 다국어, 공통으로 처리해야 하는 기능이 구현되어 있다고 하자

                    // uri == hello, method == get
                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
                        String name = req.getParameter("name");

                        HelloController helloController = applicationContext.getBean(HelloController.class);
                        String ret = helloController.hello(name);

                        // 응답에 관한 코드
                        resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
                        // 바디
                        resp.getWriter().println(ret);
                    } else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }

                }
//                / 밑으로 들어오는 모든 요청을 처리하겠다. (front controller 책임을 맡게 된다.)
            }).addMapping("/*");
        });
        // 톰캣 서블릿 컨테이너가 동작한다.
        webServer.start();

        System.out.println("Hello containerless Standalone Application");
    }
}
