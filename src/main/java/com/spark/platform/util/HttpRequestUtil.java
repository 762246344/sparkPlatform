package com.spark.platform.util;

import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by dengfangyuan on 2017/1/6.
 */
public class HttpRequestUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);    //日志记录

    /**
     * 发送get请求
     *
     * @param url 路径
     * @return
     */
    public static JSONObject httpGet(String url) {
        //get请求返回结果
        JSONObject jsonObject = null;
        try {
            DefaultHttpClient client = new SSLClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String strResult = EntityUtils.toString(response.getEntity());
                jsonObject = JSONObject.fromObject(strResult);
            } else {
                logger.warn("get请求提交失败:" + url);
            }
        } catch (Exception e) {
            logger.warn("get请求提交失败:" + url, e);
        }
        return jsonObject;
    }

    public static JSONObject httpPost(String jsonString, String url) throws RuntimeException {
        JSONObject jsonObject = null;
        try {
            DefaultHttpClient client = new SSLClient();
            HttpPost request = new HttpPost(url);
            HttpEntity requestEntity = new StringEntity(jsonString, "application/json", "utf-8");
            request.setEntity(requestEntity);
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK || response.getStatusLine().getStatusCode() == 201) {
                String strResult = EntityUtils.toString(response.getEntity());
                jsonObject = JSONObject.fromObject(strResult);
            } else {
                logger.warn("post请求提交失败:" + url);
            }
        } catch (Exception e) {
            logger.warn("post请求提交失败:" + url, e);
        }
        return jsonObject;
    }

    public static JSONObject httpPostToken(String jsonString, String url, String token) throws RuntimeException {
        JSONObject jsonObject = null;
        try {
            DefaultHttpClient client = new SSLClient();
            HttpPost request = new HttpPost(url);
            HttpEntity requestEntity = new StringEntity(jsonString, "application/json", "utf-8");
            request.setEntity(requestEntity);
            request.setHeader("X-Auth-Token", token);
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String strResult = EntityUtils.toString(response.getEntity());
                jsonObject = JSONObject.fromObject(strResult);
            } else {
                logger.warn("post请求提交失败:" + url);
            }
        } catch (Exception e) {
            logger.warn("post请求提交失败:" + url, e);
        }
        return jsonObject;
    }

    /**
     * 描述:获取 post 请求的 byte[] 数组
     * <pre>
     * 举例：
     * </pre>
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {

            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

    /**
     * 描述:获取 post 请求内容
     * <pre>
     * 举例：
     * </pre>
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static String getRequestPostStr(HttpServletRequest request)
            throws IOException {
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }

    public static String getRequestJson(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        return stringBuilder.toString();
    }
}