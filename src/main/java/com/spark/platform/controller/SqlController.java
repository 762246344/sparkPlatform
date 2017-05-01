package com.spark.platform.controller;

import com.spark.platform.model.Info;
import com.spark.platform.model.Sql;
import com.spark.platform.service.SqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouqi
 * @since 2016-03-19 11:10
 */
@RestController
@RequestMapping("/sql")
public class SqlController {
    @Autowired
    private SqlService sqlService;

    @RequestMapping(value = "/query", method = RequestMethod.POST, consumes = "application/json")
    public Info ownList(@RequestBody Sql sql) {
        return sqlService.query(sql.getSql());
    }

    @RequestMapping(value = "/allTable", method = RequestMethod.GET)
    public Info getAllTables() {
        return sqlService.getAllTables();
    }

}
