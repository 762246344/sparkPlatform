package com.spark.platform.service;

import com.spark.platform.model.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * @author zhaoguoxian
 * @since 2015-12-19 11:09
 */

@Service
public class SqlService {

    private static Logger LOGGER = LoggerFactory.getLogger(SqlService.class);

    public Info query(String sql) {
        String jdbcdriver = "org.apache.hive.jdbc.HiveDriver"; //hive jdbc驱动
        String jdbcurl = "jdbc:hive2://master:10000"; //thriftserver url
        String username = ""; //用户名 为空即可
        String password = ""; //密码 为空即可
        Connection conn = null;
        try {
            Class.forName(jdbcdriver); //加载hive jdbc驱动
            conn = DriverManager.getConnection(jdbcurl, username, password); //获取连接
            Statement st = conn.createStatement(); //创建状态对象
            ResultSet res = null;
            LOGGER.info(sql);
            res = st.executeQuery(sql); //执行sql语句返回结果
            List<String[]> list = new ArrayList<String[]>();
            String[] s = new String[res.getMetaData().getColumnCount()];
            for (int i = 0; i < s.length; i++) { //遍历表头
                s[i] = res.getMetaData().getColumnName(i + 1);
            }
            list.add(s);
            while (res.next()) {   //遍历结果加到list中
                String[] ss = new String[res.getMetaData().getColumnCount()];
                for (int i = 0; i < s.length; i++) {
                    ss[i] = res.getString(i + 1);
                }
                list.add(ss);
            }
            return new Info("0", "success", list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Info("1", "Exception:" + e.getMessage(), null);
        }
    }

    public Info getAllTables() {
        String sql = "show tables"; //固定sql获取所有表
        String jdbcdriver = "org.apache.hive.jdbc.HiveDriver"; //hive jdbc驱动
        String jdbcurl = "jdbc:hive2://master:10000"; //thriftserver url
        String username = ""; //用户名 为空即可
        String password = ""; //密码 为空即可
        Connection conn = null;
        try {
            Class.forName(jdbcdriver); //加载hive jdbc驱动
            conn = DriverManager.getConnection(jdbcurl, username, password);  //获取连接
            Statement st = conn.createStatement(); //创建状态对象
            ResultSet res = null;
            LOGGER.info(sql);
            res = st.executeQuery(sql); //查询所有表返回结果
            List<String> list = new ArrayList<String>();
            while (res.next()) { //遍历结果加到list中
                list.add(res.getString("tableName"));
            }
            return new Info("0", "success", list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Info("1", "Exception:" + e.getMessage(), null);
        }
    }
}

