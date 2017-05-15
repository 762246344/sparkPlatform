package com.spark.platform.service;

import com.spark.platform.model.Info;
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

    public Info query(String sql) {
        String jdbcdriver = "org.apache.hive.jdbc.HiveDriver";
        String jdbcurl = "jdbc:hive2://master:10000";
        String username = "";
        String password = "";
        Connection conn = null;
        try {
            Class.forName(jdbcdriver);
            conn = DriverManager.getConnection(jdbcurl, username, password);
            Statement st = conn.createStatement();
            ResultSet res = null;
            //System.out.println(sql);
            res = st.executeQuery(sql);
            List<String[]> list = new ArrayList<String[]>();
            String[] s = new String[res.getMetaData().getColumnCount()];
            for (int i = 0; i < s.length; i++) {
                s[i] = res.getMetaData().getColumnName(i + 1);
            }
            list.add(s);
            while (res.next()) {
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
        String sql = "show tables";
        String jdbcdriver = "org.apache.hive.jdbc.HiveDriver";
        String jdbcurl = "jdbc:hive2://master:10000";
        String username = "";
        String password = "";
        Connection conn = null;
        try {
            Class.forName(jdbcdriver);
            conn = DriverManager.getConnection(jdbcurl, username, password);
            Statement st = conn.createStatement();
            ResultSet res = null;
            System.out.println(sql);
            res = st.executeQuery(sql);
            List<String> list = new ArrayList<String>();
            while (res.next()) {
                list.add(res.getString(1));
            }
            return new Info("0", "success", list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Info("1", "Exception:" + e.getMessage(), null);
        }
    }
}

