package com.spark.platform.service;

import com.spark.platform.model.Info;
import org.springframework.stereotype.Service;



@Service
public class HdfsService {

    public Info ownList() {
        return new Info("0","",null);
    }

}
