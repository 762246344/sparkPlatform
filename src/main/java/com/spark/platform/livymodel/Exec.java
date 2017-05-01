package com.spark.platform.livymodel;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by zhouqi on 2017/3/29.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Exec {
    private String kind;
    private String code;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
