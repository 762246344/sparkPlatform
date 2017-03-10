package com.spark.platform.service;

import com.spark.platform.model.Info;
import org.springframework.stereotype.Service;


/**
 * @author zhaoguoxian
 * @since 2015-12-19 11:09
 */

@Service
public class HdfsService {

    public Info ownList() {
        return new Info("0","",null);
    }

}
