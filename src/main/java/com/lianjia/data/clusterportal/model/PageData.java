package com.lianjia.data.clusterportal.model;

import java.util.List;

/**
 * Created by zhouqi on 2017/1/4.
 */
public class PageData {
    private Integer pageNum;
    private Integer pageCount;
    private Integer pageSize;
    private List<Object> dataList;

    public PageData() {
    }

    public PageData(Integer pageNum, Integer pageCount, Integer pageSize, List<Object> dataList) {
        this.pageNum = pageNum;
        this.pageCount = pageCount;
        this.pageSize = pageSize;
        this.dataList = dataList;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<Object> getDataList() {
        return dataList;
    }

    public void setDataList(List<Object> dataList) {
        this.dataList = dataList;
    }
}
