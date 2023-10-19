package com.presscentric.presscentrictestproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PressCentricTestProjectApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PressCentricTestProjectApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(PressCentricTestProjectApplication.class, args);
    }

}
