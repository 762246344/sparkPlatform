package com.spark.platform.test.service;

import com.spark.platform.service.HdfsService;
import org.junit.Test;


/**
 * Created by zhouqi on 2017/5/1.
 */
public class HdfsServiceTest {

    @Test
    public void getSubmitFilesTest() {
        System.out.println(new HdfsService().getSubmitFiles());
    }

    public void test(){
    }
}
