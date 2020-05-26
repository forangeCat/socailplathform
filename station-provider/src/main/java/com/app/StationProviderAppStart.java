package com.app;

import com.app.station.config.RootContextConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StationProviderAppStart {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RootContextConfig.class);
        context.start();
//        UserService bean = context.getBean(UserService.class);
//        CacheManager cacheManager = context.getBean(CacheManager.class);
//        Cache cache = cacheManager.getCache("user");
//        cache.put("woaini","woaini");
//        System.out.println(cacheManager);
        System.out.println("sss");
        System.in.read();
    }
}
