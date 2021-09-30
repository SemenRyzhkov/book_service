package com.github.template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.activation.DataSource;

public class DataSourceTestConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSourceConfig = new DriverManagerDataSource();
        dataSourceConfig.setDriverClassName("org.postgresql.Driver");
        dataSourceConfig.setUrl("jdbc:postgresql://127.0.0.1:5432/book_service");
        dataSourceConfig.setUsername("postgres");
        dataSourceConfig.setPassword("postgres");

        return (DataSource) dataSourceConfig;
    }
}
