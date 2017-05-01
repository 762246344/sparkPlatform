package com.spark.platform.test.util;

import com.spark.platform.util.HdfsUtil;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by zhouqi on 2017/5/1.
 */
public class HdfsTest {

    @Test
    public void test(){
        try {
            HdfsUtil.printDir(new Path("/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
