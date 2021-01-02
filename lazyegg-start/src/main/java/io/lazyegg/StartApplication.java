package io.lazyegg;

import io.lazyegg.auth.infrastructure.config.EnableShiroConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Spring Boot Starter
 */
@SpringBootApplication
@EnableAsync
@EnableShiroConfig
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }
}
