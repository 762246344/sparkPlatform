package com.spark.platform.livymodel;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

/**
 * Created by zhouqi on 2017/3/28.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Batch {
    private Integer id;
    private String appId;
    private AppInfo appInfo;
    private String state;
    private List<String> log;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", appId='" + appId + '\'' +
                ", appInfo=" + appInfo +
                ", state='" + state + '\'' +
                ", log=" + log +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public AppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<String> getLog() {
        return log;
    }

    public void setLog(List<String> log) {
        this.log = log;
    }
}
