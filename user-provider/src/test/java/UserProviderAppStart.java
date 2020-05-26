import com.app.user.config.RootContextConfig;
import org.junit.Test;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserProviderAppStart {
    @Test
    public void contextLoad() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:provider.xml");
        context.start();
        CacheManager cacheManager = context.getBean(CacheManager.class);
        System.out.println(cacheManager);
    }
}
