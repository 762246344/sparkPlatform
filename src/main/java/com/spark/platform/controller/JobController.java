package com.spark.platform.controller;

import com.spark.platform.model.Info;
import com.spark.platform.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author zhouqi
 * @since 2016-3-19 11:10
 */
@RestController
public class JobController {
    @Autowired
    private JobService jobService;

    @RequestMapping(value = "/batch", method = RequestMethod.POST, consumes = "application/json")
    public Info batch(@RequestBody Map<String, Object> batchReq) {
        return jobService.batch(batchReq);
    }

    @RequestMapping(value = "/getJobInfo", method = RequestMethod.GET)
    public Info getJobInfo() {
        return jobService.getJobInfo();
    }
}
