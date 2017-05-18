package com.spark.platform.schedule;

import com.spark.platform.livymodel.Batch;
import com.spark.platform.mapper.TaskMapper;
import com.spark.platform.model.Task;
import com.spark.platform.util.LivyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zhouqi on 2017/5/17.
 */
@Component
public class TaskSchedule {

    private static Logger LOGGER = LoggerFactory.getLogger(TaskSchedule.class);

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private LivyUtil livyUtil;

    @Scheduled(fixedRate = 1000)
    public void flushJob() {
        Task selectTask = new Task();
        selectTask.setActive(true);
        List<Task> tasks = taskMapper.select(selectTask);
        for (Task task : tasks) {
            boolean flag = false;
            Batch batch = livyUtil.getBatch(task.getBatchId());
            if (task.getAppId() == null || task.getAppId().equals("")) {
                task.setAppId(batch.getAppId());
                flag = true;
            }
            if (task.getYarnUrl() == null || task.getYarnUrl().equals("")) {
                task.setYarnUrl(batch.getAppInfo().getSparkUiUrl());
                flag = true;
            }
            if (task.getState() != batch.getState()) {
                task.setState(batch.getState());
                flag = true;
            }
            if (batch.getState().equals("success") || batch.getState().equals("dead")) {
                task.setActive(false);
                task.setStopTime(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
                taskMapper.updateActiveFalse(task);
                flag = true;
            }
            if (flag == true) {
                taskMapper.updateById(task);
                LOGGER.info(task.toString());
            }
        }
    }
}
