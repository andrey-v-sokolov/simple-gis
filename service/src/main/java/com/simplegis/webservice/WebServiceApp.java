package com.simplegis.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * Temporary javadoc.
 */
@ComponentScan("com.simplegis.webservice")
@PropertySource(
        value = {"classpath:application.properties", "file:${CATALINA_HOME:/opt/tomcat}/conf/application.properties"},
        ignoreResourceNotFound = true
)
@SpringBootApplication
public class WebServiceApp {

    /**
     * Temporary javadoc.
     * @param args application startup arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(WebServiceApp.class, args);
    }
}
