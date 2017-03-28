package com.spark.platform.controller;

import com.spark.platform.model.Info;
import com.spark.platform.model.Sql;
import com.spark.platform.service.SqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhaoguoxian
 * @since 2015-12-19 11:10
 */
@RestController
@RequestMapping("/sql")
public class SqlController {
    @Autowired
    private SqlService sqlService;

    @RequestMapping(value = "/query",method = RequestMethod.POST, consumes = "application/json")
    public Info ownList(@RequestBody Sql sql) {
        return sqlService.query(sql.getSql());
    }

}
