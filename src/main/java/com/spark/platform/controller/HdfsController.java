package com.spark.platform.controller;

import com.spark.platform.model.Info;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhaoguoxian
 * @since 2015-12-19 11:10
 */
@RestController
@RequestMapping("/hdfs")
public class HdfsController {

    @RequestMapping(value = "/own/list")
    public Info ownList(@RequestParam(value = "username", required = true) String username) {
        return new Info("0","",null);
    }

}
