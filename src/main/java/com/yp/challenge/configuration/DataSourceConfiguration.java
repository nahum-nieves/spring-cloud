package com.yp.challenge.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
public class DataSourceConfiguration {

    @Bean
    public DataSource comboPooledDataSource(DataSourceConfigurationProperties dataSourceConfiguration) throws PropertyVetoException {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        ds.setDriverClass(dataSourceConfiguration.getDriverClassName());
        ds.setJdbcUrl(dataSourceConfiguration.getUrl());
        ds.setUser(dataSourceConfiguration.getUsername());
        ds.setPassword(dataSourceConfiguration.getPassword());
        return ds;
    }
}
