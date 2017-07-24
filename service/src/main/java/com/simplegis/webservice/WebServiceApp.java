package com.simplegis.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Application endpoint for Spring-Boot.
 */
@ComponentScan("com.simplegis.webservice")
@PropertySource(
        value = {"classpath:application.properties", "file:${CATALINA_HOME:/opt/tomcat}/conf/application.properties"},
        ignoreResourceNotFound = true
)
@EnableTransactionManagement
@SpringBootApplication
public class WebServiceApp {

    /**
     * Application entry point.
     *
     * @param args application arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(WebServiceApp.class, args);
    }
}
