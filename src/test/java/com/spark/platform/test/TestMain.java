package com.spark.platform.test;

import com.spark.platform.util.LivyUtil;
import com.sun.corba.se.spi.activation.TCPPortHelper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import sun.rmi.transport.tcp.TCPConnection;

import javax.rmi.PortableRemoteObject;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhouqi on 2017/3/11.
 */
public class TestMain {

    @Test
    public void testlivy(){
        System.out.println(new LivyUtil().createSession("pyspark3"));
    }
    @Test
    public void test() {
        String[] words = "we test coders".split(" ");
        String res = "";
        for (int i = 0; i < words.length; i++) {
            char[] a = words[i].toCharArray();
            for (int j = 0; j < a.length / 2; j++) {
                char temp = a[j];
                a[j] = a[a.length - 1 - j];
                a[a.length - 1 - j] = temp;
            }
            res += String.valueOf(a) + " ";
        }
        System.out.println(res.trim());

    }

    @Test
    public void test2() {
        int[] a = {1, 2, 3, 4, 4, 4, 4};
        System.out.println(solution(a, 4));
    }

    int solution(int[] A, int X) {
        int N = A.length;
        if (N == 0) {
            return -1;
        }
        int l = 0;
        int r = N - 1;
        while (l < r) {
            int m = (l + r + 1) / 2;
            if (A[m] > X) {
                r = m - 1;
            } else {
                l = m;
                System.out.println(l);
            }
        }
        if (A[l] == X) {
            return l;
        }
        return -1;
    }

    @Test
    public void test3() {
        try {
            Socket client = new Socket();
            client.connect(new InetSocketAddress("test-bd-hadoop00.lianjia.com", 12345), 100);
            System.out.println("端口已开放");
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("端口未开放");
        }
    }

    @Test
    public void test4() {
        String jdbcdriver = "org.apache.hive.jdbc.HiveDriver";
        String jdbcurl = "jdbc:hive2://master:10000/default";
        String username = "";

        String password = "";
        Connection conn = null;
        String sql = "desc log";
        try {
            new Socket("master", 10000);
            Class.forName(jdbcdriver);
            conn = DriverManager.getConnection(jdbcurl, username, password);
            Statement st = conn.createStatement();
            ResultSet res = null;
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
            System.out.println(JSONArray.fromObject(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
