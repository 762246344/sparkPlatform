package com.spark.platform.aspect;

import net.sf.json.JSONArray;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhouqi on 2017/1/4.
 */
@Aspect
@Component
public class ServiceAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * com.spark.platform.service.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logger.info("service:"+joinPoint.getTarget().getClass().getSimpleName()+"; operation:" + joinPoint.getSignature().getName() + "; args:" + JSONArray.fromObject(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        logger.info("result:" + JSONArray.fromObject(ret));
    }

}
