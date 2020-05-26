package javaconfig;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@EnableDubbo
@DubboComponentScan("controller")
@Configuration
@PropertySource("classpath:dubbo-spring.properties")
public class DubboConfig  {

}
