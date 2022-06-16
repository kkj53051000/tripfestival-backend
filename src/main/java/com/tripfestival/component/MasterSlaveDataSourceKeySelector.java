package com.tripfestival.component;

import java.util.ArrayList;
import java.util.List;

public class MasterSlaveDataSourceKeySelector {
    private Object masterKey;
    private List<Object> slaveKeys = new ArrayList<>();
    private int slaveIdx = 0;

    public void setMasterKey(Object masterKey) {
        this.masterKey = masterKey;
    }

    public void addSlaveKey(Object slaveKey) {
        this.slaveKeys.add(slaveKey);
    }

    public Object getMasterKey() {
        return masterKey;
    }

    public Object getSlaveKey() {
        this.slaveIdx = (this.slaveIdx + 1) % slaveKeys.size();
        return slaveKeys.get(this.slaveIdx);
    }
}
