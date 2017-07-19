package com.simplegis.webservice;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.simplegis")
@PropertySource(value = {"classpath:application.properties"})
@EnableAutoConfiguration
public class TestConfig {
}