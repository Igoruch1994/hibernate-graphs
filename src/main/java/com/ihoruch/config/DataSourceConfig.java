package com.ihoruch.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class DataSourceConfig {

    private static final int CONNECTION_TIMEOUT = 80000;
    private static final int MAXIMUM_POOL_SIZE = 5;

    private final DataSourceProperties dataSourceProperties;

    @Bean
    @Primary
    public DataSource dataSource() {
        final HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setUsername(dataSourceProperties.getUsername());
        hikariDataSource.setPassword(dataSourceProperties.getPassword());
        hikariDataSource.setJdbcUrl(dataSourceProperties.getUrl());
        hikariDataSource.setConnectionTimeout(CONNECTION_TIMEOUT);
        hikariDataSource.setMaximumPoolSize(MAXIMUM_POOL_SIZE);
        hikariDataSource.setPoolName("PetProjectPool");
        return hikariDataSource;
    }

}
