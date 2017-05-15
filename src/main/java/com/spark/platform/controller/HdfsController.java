package com.spark.platform.controller;

import com.spark.platform.model.Info;
import com.spark.platform.service.HdfsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


@RestController
@RequestMapping("/hdfs")
public class HdfsController {

    @Autowired
    private HdfsService hdfsService;

    @RequestMapping(value = "/own/list")
    public Info ownList(@RequestParam(value = "username", required = true) String username) {
        return new Info("0", "", null);
    }

    @RequestMapping(value = "/upload")
    public Info upload(@RequestParam(value = "file", required = false) MultipartFile file) {
        return hdfsService.put(file);
    }

    @RequestMapping(value = "/getFile", method = RequestMethod.GET)
    public Info getFile() {
        return hdfsService.getSubmitFiles();
    }

}
