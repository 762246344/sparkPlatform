package com.spark.platform.service;

import com.spark.platform.livymodel.AppInfo;
import com.spark.platform.livymodel.Batch;
import com.spark.platform.mapper.TaskMapper;
import com.spark.platform.model.Info;
import com.spark.platform.model.Task;
import com.spark.platform.util.LivyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class JobService {
    private static Logger LOGGER = LoggerFactory.getLogger(JobService.class);

    @Autowired
    private LivyUtil livyUtil;

    @Autowired
    private TaskMapper taskMapper;

    public Info batch(Map<String, Object> batchReq) {
        String errNo = "0";
        String errMsg = "Success";
        if (batchReq == null ||
                batchReq.size() == 0) {
            return new Info("1", "args is null", null);
        }
        try {
            String type = batchReq.get("type").toString();
            batchReq.remove("type");
            Batch batch = livyUtil.createBatch(batchReq);
            Task task = new Task();
            task.setName(batchReq.get("name").toString());
            task.setType(type);
            task.setBatchId(batch.getId());
            task.setState(batch.getState());
            task.setStartTime(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
            task.setActive(true);
            taskMapper.insert(task);
        } catch (Exception e) {
            LOGGER.error("", e);
            errNo = "1";
            errMsg = e.getMessage();
        }
        return new Info(errNo, errMsg, null);
    }

    public Info getJobInfo() {
        String errNo = "0";
        String errMsg = "Success";
        AppInfo appInfo = null;
        List<Task> list = null;
        try {
            list = taskMapper.selectAll();
        } catch (Exception e) {
            errNo = "1";
            errMsg = e.getMessage();
            LOGGER.error("", e);
        }
        return new Info(errNo, errMsg, list);
    }
}
