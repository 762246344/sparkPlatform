package com.spark.platform.controller;

import com.spark.platform.livymodel.Exec;
import com.spark.platform.model.Info;
import com.spark.platform.service.ShellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhouqi
 * @since 2016-3-19 11:10
 */
@RestController
public class ShellController {
    @Autowired
    private ShellService shellService;

    @RequestMapping(value = "/session/exec", method = RequestMethod.POST, consumes = "application/json")
    public Info exec(@RequestBody Exec exec) {
        return shellService.exec(exec);
    }

    @RequestMapping(value = "/session/getUrl", method = RequestMethod.GET)
    public Info getUrl(@RequestParam String type) {
        return shellService.getUrl(type);
    }
}
