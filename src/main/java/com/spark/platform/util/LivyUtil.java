package com.spark.platform.util;

import com.spark.platform.livymodel.Session;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
    private String livyUri = "http://master:8998";

    public Session createSession(String kind) {
        JSONObject jo = new JSONObject();
        jo.element("kind", kind);
        return (Session) JSONObject.toBean(HttpRequestUtil.httpPost(jo.toString(), livyUri + "/sessions"), Session.class);
    }
    @Scheduled(fixedRate=1000*60)
    public void flushSession() {
        System.out.println(sSession+" "+pSession);
        JSONArray ja = HttpRequestUtil.httpGet(livyUri + "/sessions").getJSONArray("sessions");
        Map<String, Session> map = new HashMap<String, Session>();
        for (int i = 0; i < ja.size(); i++) {
            Session session = (Session) JSONObject.toBean(ja.getJSONObject(i), Session.class);
            if (session.getState().equals("idle")) {
                map.put(session.getKind(), session);
                System.out.println(session.getKind());
            }
        }
        if (map.get("spark") != null) {
            sSession = map.get("spark");
        } else {
            sSession = createSession("spark");
        }
        if (map.get("pyspark") != null) {
            pSession = map.get("pyspark");
        } else {
            pSession = createSession("pyspark");
        }
        System.out.println(sSession+" "+pSession);
    }
}
