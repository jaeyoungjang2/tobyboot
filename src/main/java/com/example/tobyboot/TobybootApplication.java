package com.example.tobyboot;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;

//@SpringBootApplication
public class TobybootApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(TobybootApplication.class, args);
//    }
public static void main(String[] args) {
    TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
    // TomcatServletWebServerFactory serverFactory = new JettyServletWebServerFactory();
    WebServer webServer = serverFactory.getWebServer();
    // 톰캣 서블릿 컨테이너가 동작한다.
    webServer.start();

    System.out.println("Hello containerless Standalone Application");
}

}
