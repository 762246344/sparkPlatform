package com.spark.platform.service;

import com.spark.platform.model.Info;
import com.spark.platform.util.HdfsUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


@Service
public class HdfsService {

    public Info ownList() {
        return new Info("0","",null);
    }

    public Info put(MultipartFile file) {
        HdfsUtil.put("/user/spark/pro/submitFile/"+file.getOriginalFilename(),file);
        return new Info("0","Success",null);
    }
}
