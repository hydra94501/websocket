package com.gallery.websoket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class WebsoketApplication {

    public static void main(String[] args) {

        SpringApplication.run(WebsoketApplication.class, args);
        System.out.println("Websoket Application Started");
    }

}