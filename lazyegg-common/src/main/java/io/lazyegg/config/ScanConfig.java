package io.lazyegg.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

/**
 * ScanConfig
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/15 10:13 下午
 */
@Configuration
@ConfigurationPropertiesScan({"io.lazyegg","com.alibaba.cola"})
public class ScanConfig {


}
