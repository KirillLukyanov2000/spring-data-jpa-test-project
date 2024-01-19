package ru.lukyanov.repository.basicversion;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class ConnectionPool {

    private final HikariDataSource ds;

     public ConnectionPool(
            @Value("${spring.datasource.url}")
            String dbUrl,

            @Value("${spring.datasource.username}")
            String dbUser,

            @Value("${spring.datasource.password}")
            String dbPassword) {

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(dbUrl);
        config.setUsername(dbUser);
        config.setPassword(dbPassword);

        ds = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
