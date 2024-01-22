package ru.lukyanov.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.Driver;

@Configuration
@ComponentScan("ru.lukyanov")
//COMMENT BELOW @ TO USE YAML RESOURCES CONFIG
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class AppConfig {


    //CONFIGURATION WITHOUT SPRING-BOOT-STARTER-JPA. WHEN USING SPRING BOOT CONFIG THE BELOW CODE CAN BE COMMENTED.
    //THEN CHECK THE DIFFERENT NAME OF HIKARI CP.
    @Bean
    DataSource dataSource(
            @Value("${spring.datasource.url}") String dbUrl,
            @Value("${spring.datasource.username}") String dbUser,
            @Value("${spring.datasource.password}") String dbPassword
    ) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setUsername(dbUser);
        config.setPassword(dbPassword);
        config.setPoolName("myHikariPool");
        return new HikariDataSource(config);
    }

    @Bean
    Driver driver(@Value("${spring.datasource.driver-class-name}") String driverClassName) throws ClassNotFoundException {
        try {
            return (Driver) Class.forName(driverClassName)
                    .getConstructor()
                    .newInstance();
        } catch (InvocationTargetException
                 | NoSuchMethodException
                 | IllegalAccessException
                 | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

}
