package com.spark.platform.model;

/**
 * Created by zhouqi on 2017/1/3.
 * The basic Info Class
 */
public class Info {

    private String errno;
    private String errmsg;
    private Object data;

    public Info() {
    }

    public Info(String errno, String errmsg, Object data) {
        this.errno = errno;
        this.errmsg = errmsg;
        this.data = data;
    }

    public String getErrno() {
        return errno;
    }

    public void setErrno(String errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Info{" +
                "errno='" + errno + '\'' +
                ", errmsg='" + errmsg + '\'' +
                ", data=" + data +
                '}';
    }
}
