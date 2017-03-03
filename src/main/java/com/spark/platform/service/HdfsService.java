package com.spark.platform.service;

import com.spark.platform.model.Info;
import com.spark.platform.model.Permission;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhaoguoxian
 * @since 2015-12-19 11:09
 */

@Service
public class HdfsService {

    public Info ownList() {
        return new Info("0","",null);
    }

    public Info ownAdd() {
        return new Info("0","",null);
    }

    public Info ownModify() {
        return new Info("0","",null);
    }

    public Info aclList() {
        List<Permission> permissionList = new ArrayList<Permission>();
        permissionList.add(new Permission("/user/username",true,true,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        permissionList.add(new Permission("/user/",true,false,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        return new Info("0","",permissionList);
    }

    public Info aclModify() {
        return new Info("0","",null);
    }

    public Info aclDelete() {
        return new Info("0","",null);
    }

    public Info aclAdd() {
        return new Info("0","",null);
    }
}
