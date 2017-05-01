package com.spark.platform.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by zhouqi on 2017/3/28.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Task {
    private Long id;
    private Integer batchId;
    private String name;
    private String appId;
    private String yarnUrl;
    private String state;
    private String type;
    private Boolean active;
    private String startTime;
    private String stopTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getYarnUrl() {
        return yarnUrl;
    }

    public void setYarnUrl(String yarnUrl) {
        this.yarnUrl = yarnUrl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }
}
