package com.app.station.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableDubbo(scanBasePackages = {"com.app.station.service"})
@Configuration
@ComponentScan("com.app.station.service")
public class DubboConfig {
}
