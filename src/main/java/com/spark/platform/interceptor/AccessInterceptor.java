package com.spark.platform.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhouqi on 2017/4/3.
 */
public class AccessInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if(!httpServletRequest.getRequestURL().toString().contains("error")){
            httpServletResponse.addHeader("Access-Control-Allow-Origin","*");
            httpServletResponse.addHeader("Access-Control-Allow-Headers","Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, ContentType,Content-Type");
            //httpServletResponse.addHeader("Access-Control-Allow-Methods","GET,POST");
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
