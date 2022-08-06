package com.letshare.msmail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MsMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsMailApplication.class, args);
    }

}
