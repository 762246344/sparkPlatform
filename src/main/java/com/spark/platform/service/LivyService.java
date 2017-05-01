package com.spark.platform.service;

import com.spark.platform.livymodel.AppInfo;
import com.spark.platform.livymodel.Batch;
import com.spark.platform.livymodel.BatchReq;
import com.spark.platform.livymodel.Exec;
import com.spark.platform.model.Info;
import com.spark.platform.util.LivyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class LivyService {

    @Autowired
    private LivyUtil livyUtil;

    public Info batch(Map<String, Object> batchReq) {
        String errNo = "0";
        String errMsg = "Success";
        Batch batch = livyUtil.createBatch(batchReq);
        return new Info(errNo, errMsg, batch);
    }

    public Info exec(Exec exec) {
        String errNo = "0";
        String errMsg = "Success";
        String res = null;
        try {
            res = livyUtil.code(exec);
        } catch (Exception e) {
            errNo = "1";
            errMsg = e.getMessage();
            e.printStackTrace();
        }
        return new Info(errNo, errMsg, res);
    }

    public Info getUrl(String type) {
        String errNo = "0";
        String errMsg = "Success";
        AppInfo appInfo = null;
        try {
            if (type.equals("scala")) {
                appInfo = LivyUtil.sSession.getAppInfo();
            } else {
                appInfo = LivyUtil.pSession.getAppInfo();
            }
        } catch (Exception e) {
            errNo = "1";
            errMsg = e.getMessage();
            e.printStackTrace();
        }
        return new Info(errNo, errMsg, appInfo);
    }
}
