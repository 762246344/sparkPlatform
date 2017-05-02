package com.spark.platform.test.service;

import com.spark.platform.service.SqlService;
import net.sf.json.JSONObject;
import org.junit.Test;

/**
 * Created by zhouqi on 2017/5/1.
 */
public class SqlServiceTest {

    @Test
    public void getAllTablesTest() {
        System.out.println(JSONObject.fromObject(new SqlService().getAllTables()));
    }

    @Test
    public void sqlTest() {
        System.out.println(JSONObject.fromObject(new SqlService().query("select gender,count(1) from person group by gender")));
    }
    @Test
    public void sqlTest2() {
        System.out.println(JSONObject.fromObject(new SqlService().query("desc test")));
    }


}
