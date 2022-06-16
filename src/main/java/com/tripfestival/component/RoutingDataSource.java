package com.tripfestival.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Map;

@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {
    private final MasterSlaveDataSourceKeySelector masterSlaveDataSourceKeySelector;

    public RoutingDataSource(Map<Object, Object> dataSourceMap, MasterSlaveDataSourceKeySelector masterSlaveDataSourceKeySelector) {
        super.setTargetDataSources(dataSourceMap);
        this.masterSlaveDataSourceKeySelector = masterSlaveDataSourceKeySelector;
        this.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        Object key;
        if(TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
            log.info("readOnly transaction");
            key = masterSlaveDataSourceKeySelector.getSlaveKey();
        } else {
            log.info("readWrite transaction");
            key = masterSlaveDataSourceKeySelector.getMasterKey();
        }
        log.info("current lookup key = {}", key);
        return key;
    }
}
