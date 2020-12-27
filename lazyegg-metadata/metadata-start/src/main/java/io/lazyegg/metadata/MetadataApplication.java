package io.lazyegg.metadata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Starter
 *
 * @author Frank Zhang
 */
@SpringBootApplication(scanBasePackages = {"io.lazyegg","com.alibaba.cola"})
//@MapperScan(basePackages = {"io.lazyegg.auth.infrastructure.mapper", "io.lazyegg.*.mapper"})
public class MetadataApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetadataApplication.class, args);
    }
}
