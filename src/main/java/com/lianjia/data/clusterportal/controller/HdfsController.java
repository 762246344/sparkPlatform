package com.lianjia.data.clusterportal.controller;

import com.github.pagehelper.PageInfo;
import com.lianjia.data.clusterportal.model.Info;
import com.lianjia.data.clusterportal.model.Permission;
import com.lianjia.data.clusterportal.model.UserInfo;
import com.lianjia.data.clusterportal.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhaoguoxian
 * @since 2015-12-19 11:10
 */
@RestController
@RequestMapping("/hdfs")
public class HdfsController {

    @RequestMapping(value = "/own/list")
    public Info ownList(@RequestParam(value = "username", required = true) String username) {
        return new Info("0","",null);
    }

    @RequestMapping(value = "/own/add", method = RequestMethod.POST,consumes = "application/json")
    public Info ownAdd(HttpServletRequest request) {
        return new Info("0","",null);
    }

    @RequestMapping(value = "/own/modify", method = RequestMethod.POST,consumes = "application/json")
    public Info ownModify(HttpServletRequest request) {
        return new Info("0","",null);
    }

    @RequestMapping(value = "/acl/list")
    public Info aclList(@RequestParam(value = "username", required = true) String username) {
        List<Permission> permissionList = new ArrayList<Permission>();
        permissionList.add(new Permission("/user/username",true,true,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        permissionList.add(new Permission("/user/"+username,true,false,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        return new Info("0","",permissionList);
    }

    @RequestMapping(value = "/acl/modify", method = RequestMethod.POST, consumes = "application/json")
    public Info aclModify(HttpServletRequest request) {
        request.getParameterMap();
        return new Info("0","",null);
    }

    @RequestMapping(value = "/acl/delete", method = RequestMethod.POST, consumes = "application/json")
    public Info aclDelete(HttpServletRequest request) {
        return new Info("0","",null);
    }

    @RequestMapping(value = "/acl/add", method = RequestMethod.POST, consumes = "application/json")
    public Info aclAdd(HttpServletRequest request,@RequestBody Permission permission) {
        return new Info("0","",permission);
    }

}
