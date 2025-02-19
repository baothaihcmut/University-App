package com.universityapp.common.aspect;

import ch.qos.logback.classic.Logger;
import com.universityapp.common.exception.AppException;
import com.universityapp.common.logger.LoggerUtil;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggingAspect {

    //get user info

    private final Logger logger = (Logger) LoggerFactory.getLogger(
        LoggingAspect.class.getName()
    );

    @Pointcut("execution(* com.universityapp.*.*(..))")
    public void serviceMethod() {}

    @Around("serviceMethods()")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint)
        throws Throwable {
        //get request info
        SecurityContext context = SecurityContextHolder.getContext();

        String id = context.getAuthentication().getName();
        try {
            HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            //generate request id
            String requestId = UUID.randomUUID().toString();
            request.setAttribute("request_id", requestId);

            LoggerUtil.debug(
                logger,
                "enter request",
                Map.of(
                    "method",
                    request.getMethod(),
                    "uri",
                    request.getRequestURI(),
                    "user_id",
                    id
                )
            );
            //process
            return joinPoint.proceed();
        } catch (AppException e) {
            //log error
            LoggerUtil.warn(
                logger,
                e.getMessage(),
                Map.of(
                    "status",
                    e.getStatus().toString(),
                    "detail",
                    e.getCause(),
                    "user_id",
                    id
                )
            );
            throw e;
        }
    }
}
