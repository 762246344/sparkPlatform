package com.spark.platform.util;

import com.spark.platform.livymodel.Batch;
import com.spark.platform.livymodel.Exec;
import com.spark.platform.livymodel.Session;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhouqi on 2017/3/28.
 */
@Component
public class LivyUtil {

    public static Session sSession;
    public static Session pSession;
    public static Map<Integer, Batch> batches;
    private String livyUri = "http://master:8998";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Session createSession(String kind) {
        JSONObject jo = new JSONObject();
        jo.element("kind", kind);
        return (Session) JSONObject.toBean(HttpRequestUtil.httpPost(jo.toString(), livyUri + "/sessions"), Session.class);
    }

    @Scheduled(fixedRate = 1000 * 60)
    public void flushSession() {
        JSONArray ja = HttpRequestUtil.httpGet(livyUri + "/sessions").getJSONArray("sessions");
        Map<String, Session> map = new HashMap<String, Session>();
        for (int i = 0; i < ja.size(); i++) {
            Session session = (Session) JSONObject.toBean(ja.getJSONObject(i), Session.class);
            if (session.getState().equals("idle")||session.getState().equals("starting")) {
                map.put(session.getKind(), session);
            }
        }
        if (map.get("spark") != null) {
            sSession = map.get("spark");
        } else {
            sSession = null;
            createSession("spark");
        }
        /*if (map.get("pyspark") != null) {
            pSession = map.get("pyspark");
        } else {
            pSession = null;
            createSession("pyspark");
        }*/
        if (sSession == null) {
            logger.error(sSession + " " + pSession);
        }
    }


    public String code(Exec exec) {
        Integer id = null;
        if (exec.getKind().equals("spark")) {
            id = sSession == null ? null : sSession.getId();
        } else if (exec.getKind().equals("pyspark")) {
            id = pSession == null ? null : pSession.getId();
        }
        if (id == null) {
            throw new RuntimeException("livy session is not available");
        }
        String url = livyUri + "/sessions/" + id + "/statements";
        JSONObject jo = new JSONObject();
        jo.element("code", exec.getCode());
        Integer sid = HttpRequestUtil.httpPost(jo.toString(), url).getInt("id");
        String info = "";
        while (true) {
            JSONObject res = HttpRequestUtil.httpGet(url + "/" + sid);
            if (res.getString("state").equals("available")) {
                if (res.getJSONObject("output").getString("status").equals("ok")) {
                    info = res.getJSONObject("output").getJSONObject("data").getString("text/plain");
                } else {
                    info = res.getJSONObject("output").getString("evalue");
                }
                break;
            }
        }
        try {
            Thread.currentThread().sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return info;
    }

    public Batch createBatch(Map<String, Object> batchReq) {
        String data = JSONObject.fromObject(batchReq).toString();
        System.out.println(data);
        return (Batch) JSONObject.toBean(HttpRequestUtil.httpPost(data, livyUri + "/batches"), Batch.class);
    }

    public Batch getBatch(Integer batchId) {
        return (Batch) JSONObject.toBean(HttpRequestUtil.httpGet(livyUri + "/batches/" + batchId), Batch.class);
    }

}
