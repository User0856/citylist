package com.example.citylist.dto;

public class APIResponse<T> {

    int recordCount;
    T response;

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public APIResponse() {
    }

    public APIResponse(int recordCount, T response) {
        this.recordCount = recordCount;
        this.response = response;
    }
}