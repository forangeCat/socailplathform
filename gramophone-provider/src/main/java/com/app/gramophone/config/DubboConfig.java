package com.app.gramophone.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableDubbo(scanBasePackages = {"com.app.gramophone.service"})
@Configuration
@ComponentScan("com.app.gramophone.service")
public class DubboConfig {
}
