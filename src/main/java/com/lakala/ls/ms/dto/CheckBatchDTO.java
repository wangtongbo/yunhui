package com.lakala.ls.ms.dto;

/**
 * Created by wenhemin on 2017/5/16.
 */
public class CheckBatchDTO {

    private String batchId;

    private String clearDate;

    private String state;

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getClearDate() {
        return clearDate;
    }

    public void setClearDate(String clearDate) {
        this.clearDate = clearDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
