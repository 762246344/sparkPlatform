package com.spark.platform.livymodel;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhouqi on 2017/3/28.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Session {
    private Integer id;
    private Integer appId;
    private String owner;
    private String proxyUser;
    private String state;
    private AppInfo appInfo;
    private String kind;
    private List<String> log;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getProxyUser() {
        return proxyUser;
    }

    public void setProxyUser(String proxyUser) {
        this.proxyUser = proxyUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<String> getLog() {
        return log;
    }

    public void setLog(List<String> log) {
        this.log = log;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public AppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", proxyUser='" + proxyUser + '\'' +
                ", state='" + state + '\'' +
                ", kind='" + kind + '\'' +
                ", log=" + log +
                '}';
    }
}
