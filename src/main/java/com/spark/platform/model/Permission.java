package com.spark.platform.model;

import java.util.Date;

/**
 * Created by zhouqi on 2017/1/3.
 */
public class Permission {
    private String path;
    private boolean file;
    private boolean readonly;
    private String date;

    public Permission() {
    }

    public Permission(String path, boolean file, boolean readonly, String date) {
        this.path = path;
        this.file = file;
        this.readonly = readonly;
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isIsFile() {
        return file;
    }

    public void setIsFile(boolean file) {
        this.file = file;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
