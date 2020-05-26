package com.app.gramophone.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
@Import({DubboConfig.class,RedisConfig.class,MybatisConfig.class})
//@ImportResource("classpath:spring.xml")
@Configuration
@EnableCaching
public class RootContextConfig {

}
