package com.spark.platform.model;

/**
 * Created by zhaoguoxian on 9/1/17.
 */
public class UserInfo extends  BaseEntity{

    private String username;
    private String productionLine;
    private String contacts;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProductionLine() {
        return productionLine;
    }

    public void setProductionLine(String productionLine) {
        this.productionLine = productionLine;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

}
