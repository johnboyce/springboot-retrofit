package com.comcast.oktest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OktestApplication {

    public static void main(String[] args) {
        SpringApplication.run(OktestApplication.class, args);
    }

}
