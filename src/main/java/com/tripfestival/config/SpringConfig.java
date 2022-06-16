package com.tripfestival.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import property.MainDataSourceProperty;

@ConfigurationPropertiesScan("com.tripfestival")
@EnableConfigurationProperties // ConfigurationProperties 기능 활성하
@Configuration
public class SpringConfig {
    @Bean
    public MainDataSourceProperty mainDataSourceProperty() {
        return new MainDataSourceProperty();
    }
}
