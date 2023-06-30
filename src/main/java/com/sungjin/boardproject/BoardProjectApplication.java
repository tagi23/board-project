package com.sungjin.boardproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

@ConfigurationPropertiesScan
@SpringBootApplication
public class BoardProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardProjectApplication.class, args);
    }

}
