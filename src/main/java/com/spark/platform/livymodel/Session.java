package com.spark.platform.livymodel;

import java.util.Arrays;

/**
 * Created by zhouqi on 2017/3/28.
 */
public class Session {
    private Integer id;
    private String owner;
    private String proxyUser;
    private String state;
    private String kind;
    private String[] log;

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

    public String[] getLog() {
        return log;
    }

    public void setLog(String[] log) {
        this.log = log;
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

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", proxyUser='" + proxyUser + '\'' +
                ", state='" + state + '\'' +
                ", kind='" + kind + '\'' +
                ", log=" + Arrays.toString(log) +
                '}';
    }
}
