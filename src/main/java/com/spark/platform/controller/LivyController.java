package com.spark.platform.controller;

import com.spark.platform.livymodel.BatchReq;
import com.spark.platform.livymodel.Exec;
import com.spark.platform.model.Info;
import com.spark.platform.service.LivyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author zhouqi
 * @since 2016-3-19 11:10
 */
@RestController
public class LivyController {
    @Autowired
    private LivyService livyService;

    @RequestMapping(value = "/session/exec", method = RequestMethod.POST, consumes = "application/json")
    public Info exec(@RequestBody Exec exec) {
        return livyService.exec(exec);
    }

    @RequestMapping(value = "/session/getUrl", method = RequestMethod.GET)
    public Info getUrl(@RequestParam String type) {
        return livyService.getUrl(type);
    }

    @RequestMapping(value = "/batch", method = RequestMethod.POST, consumes = "application/json")
    public Info batch(@RequestBody Map<String, Object> batchReq) {
        return livyService.batch(batchReq);
    }

    @RequestMapping(value = "/getJobInfo", method = RequestMethod.GET)
    public Info getJobInfo() {
        return livyService.getJobInfo();
    }
}
