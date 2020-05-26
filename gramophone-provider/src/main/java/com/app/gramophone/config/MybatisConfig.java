package com.app.gramophone.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableTransactionManagement
@org.springframework.context.annotation.Configuration
public class MybatisConfig {

    @Value("${mysql.user}")
     String username;
    @Value("${mysql.password}")
     String password;
    @Value("${mysql.url}")
     String url;
    @Value("${mysql.driver}")
     String driver;
    @Bean
    public DataSource druidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        dataSource.setUrl(url);
//        dataSource.setDriverClassName(driver);
        dataSource.setUsername("root");
        dataSource.setPassword("pengyinming");
        dataSource.setUrl("jdbc:mysql://47.98.47.2:3306/anonymous_social_plathform");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean( );
        bean.setDataSource(druidDataSource());
        bean.setConfiguration(sqlConfiguration());
        bean.setTypeAliasesPackage("app.generator.mapper");
        return bean;

    }

    @Bean
    public Configuration sqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.addMappers("app.generator.mapper");
        return configuration;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() throws Exception {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("app.generator.mapper");
        configurer.setSqlSessionFactory(sqlSessionFactoryBean().getObject());
        return configurer;
    }
}
