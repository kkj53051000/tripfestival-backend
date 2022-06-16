package com.tripfestival.config;

import com.tripfestival.component.MasterSlaveDataSourceKeySelector;
import com.tripfestival.component.RoutingDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import property.MainDataSourceProperty;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class DataSourceConfig {
    private final MainDataSourceProperty mainDataSourceProperty;

    private DataSource createDataSource(String url, String username, String password) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setMaximumPoolSize(20);
        config.setMinimumIdle(20);
        return new HikariDataSource(config);
    }

    @Bean
    DataSource dataSource() {
        MasterSlaveDataSourceKeySelector masterSlaveDataSourceKeySelector = new MasterSlaveDataSourceKeySelector();
        Map<Object, Object> dataSourceMap = new HashMap<>();

        // master
        DataSource masterDataSource = createDataSource(
                mainDataSourceProperty.getMaster().getUrl(),
                mainDataSourceProperty.getUsername(),
                mainDataSourceProperty.getPassword());
        dataSourceMap.put(mainDataSourceProperty.getMaster().getName(), masterDataSource);
        masterSlaveDataSourceKeySelector.setMasterKey(mainDataSourceProperty.getMaster().getName());

        // slaves
        mainDataSourceProperty.getSlaves().forEach(database -> {
            DataSource slaveDataSource = createDataSource(
                    database.getUrl(),
                    mainDataSourceProperty.getUsername(),
                    mainDataSourceProperty.getPassword()
            );
            dataSourceMap.put(database.getName(), slaveDataSource);
            masterSlaveDataSourceKeySelector.addSlaveKey(database.getName());
        });

        AbstractRoutingDataSource routingDataSource = new RoutingDataSource(dataSourceMap, masterSlaveDataSourceKeySelector);
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }

}
