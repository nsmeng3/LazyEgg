package io.lazyegg;

import io.lazyegg.auth.infrastructure.config.EnableShiroConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableShiroConfig
@EnableAsync
public class WarApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(WarApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WarApplication.class);
    }

}
