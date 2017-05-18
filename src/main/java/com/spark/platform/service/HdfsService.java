package com.spark.platform.service;

import com.spark.platform.model.Info;
import com.spark.platform.util.HdfsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


@Service
public class HdfsService {

    private static Logger LOGGER = LoggerFactory.getLogger(HdfsService.class);

    public Info put(MultipartFile file) {
        try {
            HdfsUtil.put("/user/spark/pro/submitFile/" + file.getOriginalFilename(), file);
        } catch (IOException e) {
            LOGGER.warn("", e);
            return new Info("0", "upload error", null);
        }
        return new Info("0", "Success", null);
    }

    public Info getSubmitFiles() {
        return new Info("0", "Success", HdfsUtil.getFiles("/user/spark/pro/submitFile/"));
    }

    public Info delete(String path) {
        try {
            HdfsUtil.delete(path, false);
        } catch (IOException e) {
            LOGGER.warn("", e);
            return new Info("0", "delete hdfs file error", null);
        }
        return new Info("0", "Success", null);
    }
}
