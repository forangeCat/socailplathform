package com.app.user;

import com.app.user.config.RootContextConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserProviderAppStart {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RootContextConfig.class);
        context.start();
//        UserService bean = context.getBean(UserService.class);
//        CacheManager cacheManager = context.getBean(CacheManager.class);
//        Cache cache = cacheManager.getCache("user");
//        cache.put("woaini","woaini");
//        System.out.println(cacheManager);

        System.in.read();
    }
}
