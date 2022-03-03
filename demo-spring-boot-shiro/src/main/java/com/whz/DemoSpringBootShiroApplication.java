package com.whz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @auther whz
 * @create 2022-02-28 09:24
 */
@SpringBootApplication
public class DemoSpringBootShiroApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DemoSpringBootShiroApplication.class);
        application.run();
    }
}
