package com.mobile.sensor.comm;


import com.mysql.jdbc.Driver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.SQLException;

/**
 * @author Srinivasa Prasad Sunnapu
 */
@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackageClasses = MobileSensorCommunicationApp.class)
public class MobileSensorCommunicationConfig {

    @Value("${db.host:cmpe272.c7heilvdo1fe.us-east-1.rds.amazonaws.com}")
    private String databaseHost;

    @Value("${db.name:SENSOR_CLOUD}")
    private String databaseName;

    @Value("${db.user:root}")
    private String databaseUser;

    @Value("${db.password:dbpwd}")
    private String databasePassword;

    @Bean("dataSource")
    public SimpleDriverDataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        try {
            dataSource.setDriver(new Driver());
            dataSource.setUrl("jdbc:mysql://"+databaseHost+"/"+databaseName);
            dataSource.setUsername(databaseUser);
            dataSource.setPassword(databasePassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean("jdbcTemplate")
    @DependsOn("dataSource")
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

}
