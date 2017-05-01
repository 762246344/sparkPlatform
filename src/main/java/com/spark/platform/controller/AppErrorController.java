package com.spark.platform.controller;

import com.spark.platform.model.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by zhouqi on 2017/3/3.
 */
@RestController
@RequestMapping("/error")
public class AppErrorController implements ErrorController {

    private static AppErrorController appErrorController;

    /**
     * Error Attributes in the Application
     */
    @Autowired
    private ErrorAttributes errorAttributes;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final static String ERROR_PATH = "/error";

    /**
     * Controller for the Error Controller
     *
     * @param errorAttributes
     * @return
     */

    public AppErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    public AppErrorController() {
        if (appErrorController == null) {
            appErrorController = new AppErrorController(errorAttributes);
        }
    }

    @RequestMapping
    public Info error(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> body = getErrorAttributes(request, getTraceParameter(request));
        HttpStatus status = getStatus(request);
        //return new ResponseEntity<Map<String, Object>>(body, status);
        if (response.getStatus() == 400) {
            response.setStatus(200);
        }
        if (request.getAttribute("systemId") != null) {
            logger.warn("IP:" + getIpAddress(request) + ",String parameter 'webUserName' is not present");
            return new Info(String.valueOf(status.value()), "String parameter 'webUserName' is not present", null);
        }
        logger.warn("IP:" + getIpAddress(request) + "," + body.toString());
        return new Info(String.valueOf(status.value()), body.toString(), null);
    }

    /**
     * Returns the path of the error page.
     *
     * @return the error path
     */
    public String getErrorPath() {
        return ERROR_PATH;
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    //@RequestMapping
    public Info error() {
        return new Info("1", null, null);
        //return new Info("1","illegal request",null);
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request,
                                                   boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Map<String, Object> map = this.errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
        return map;
    }

    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        if (parameter == null) {
            return false;
        }
        return !"false".equals(parameter.toLowerCase());
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode != null) {
            try {
                return HttpStatus.valueOf(statusCode);
            } catch (Exception ex) {
            }
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
