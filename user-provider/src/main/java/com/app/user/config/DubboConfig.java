package com.app.user.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableDubbo(scanBasePackages = {"com.app.user.service"})
@Configuration
@ComponentScan("com.app.user.service")
public class DubboConfig {
}
