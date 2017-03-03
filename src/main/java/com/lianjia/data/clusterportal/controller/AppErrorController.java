package com.lianjia.data.clusterportal.controller;

import com.lianjia.data.clusterportal.model.Info;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhouqi on 2017/1/3.
 */
@RestController
@RequestMapping("/error")
public class AppErrorController implements ErrorController {

    @RequestMapping
    public Info error() {
        return new Info("1","illegal request",null);                  
    }

    public String getErrorPath() {
        return null;
    }
}
