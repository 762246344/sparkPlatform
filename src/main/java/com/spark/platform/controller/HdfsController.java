package com.spark.platform.controller;

import com.spark.platform.model.Info;
import com.spark.platform.service.HdfsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@RestController
@RequestMapping("/hdfs")
public class HdfsController {

    @Autowired
    private HdfsService hdfsService;

    @RequestMapping(value = "/upload")
    public Info upload(@RequestParam(value = "file", required = false) MultipartFile file) {
        return hdfsService.put(file);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Info delete(@RequestBody Map<String, String> map) {
        return hdfsService.delete(map.get("path"));
    }

    @RequestMapping(value = "/getFile", method = RequestMethod.GET)
    public Info getFile() {
        return hdfsService.getSubmitFiles();
    }

}
