package org.example.springcontacts.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "org.example.springcontacts")
@PropertySource("classpath:application.properties")
public class AppConfig {



}
