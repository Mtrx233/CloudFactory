package com.example.consumer.bean;

import lombok.Data;

@Data
public class QueryInfo {
    String query;
    int pageNum;
    int pageSize;

    public QueryInfo(String query, int pageNum, int pageSize) {
        this.query = query;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
