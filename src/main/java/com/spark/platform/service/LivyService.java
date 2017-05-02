package com.spark.platform.service;

import com.spark.platform.livymodel.AppInfo;
import com.spark.platform.livymodel.Batch;
import com.spark.platform.livymodel.Exec;
import com.spark.platform.model.Info;
import com.spark.platform.util.LivyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class LivyService {
    private static Logger LOGGER = LoggerFactory.getLogger(LivyService.class);

    @Autowired
    private LivyUtil livyUtil;

    public Info batch(Map<String, Object> batchReq) {
        if (batchReq == null ||
                batchReq.size() == 0) {
            return new Info("1", "args is not null", null);
        }
        try {
            Batch batch = livyUtil.createBatch(batchReq);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        return new Info("0", "Success", null);
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
